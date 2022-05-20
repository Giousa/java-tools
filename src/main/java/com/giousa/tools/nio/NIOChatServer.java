package com.giousa.tools.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class NIOChatServer {

    //选择器
    private Selector selector;

    //服务端通道
    private ServerSocketChannel ssChannel;

    private static final int PORT = 9999;

    public NIOChatServer() {
        initSocket();
    }

    private void initSocket() {
        try {
            selector = Selector.open();

            ssChannel = ServerSocketChannel.open();

            ssChannel.bind(new InetSocketAddress(PORT));

            ssChannel.configureBlocking(false);

            ssChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("NIO 服务端启动成功！");
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        NIOChatServer server = new NIOChatServer();

        server.listen();
    }

    private void listen() {
        try {
            while (selector.select() > 0) {
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey sk = it.next();
                    //判断什么事件准备就绪
                    if (sk.isAcceptable()) {
                        //接收就绪，准备客户端连接
                        SocketChannel socketChannel = ssChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("客户端接成功！ " + socketChannel.getRemoteAddress());
                    } else if (sk.isReadable()) {
                        //可读准备就绪，开始读取数据
                        readClientData(sk);
                    }

                    //取消选择键
                    it.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readClientData(SelectionKey sk) {
        SocketChannel sChannel = null;
        try {
            sChannel = (SocketChannel) sk.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            StringBuffer sb = new StringBuffer();
            int len;
            while ((len = sChannel.read(buffer)) > 0) {
                buffer.flip();
                String msg = new String(buffer.array(), 0, len);
                sb.append(msg);
                buffer.clear();
            }

            if(StringUtils.isNotBlank(sb.toString())){
                System.out.println("接收客户端发来的数据：" + sb.toString());
                sendMsgToAllClient(sb.toString(), sChannel);
            }else {
                System.out.println("客户端断开V2！ " + sChannel.getRemoteAddress());
                sk.cancel();
                sChannel.close();
            }

        } catch (Exception e) {
            try {
                System.out.println("客户端断开！ " + sChannel.getRemoteAddress());
                sk.cancel();
                sChannel.close();
            } catch (Exception exception) {
                e.printStackTrace();
            }
        }

    }

    private void sendMsgToAllClient(String msg, SocketChannel sChannel) throws IOException {
        System.out.println("服务端准备转发消息给所有客户端：当前线程：" + Thread.currentThread().getName());

        for (SelectionKey key : selector.keys()) {
            //包含了ServerSocketChannel 服务端的通道
//            SocketChannel channel = (SocketChannel) key.channel();
//            if (channel == sChannel) {
//                continue;
//            }

            SelectableChannel channel = key.channel();
            if (channel instanceof SocketChannel && channel != sChannel) {
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
                ((SocketChannel) channel).write(buffer);
            }
        }
    }
}
