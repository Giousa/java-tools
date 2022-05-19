package com.giousa.tools.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class SelectorServerTest {

    public static void main(String[] args) {
        try {
            //获取通道
            ServerSocketChannel ssChannel = ServerSocketChannel.open();

            //切换非阻塞模式
            ssChannel.configureBlocking(false);

            //绑定连接
            ssChannel.bind(new InetSocketAddress(9999));

            //获取选择器
            Selector selector = Selector.open();

            //将通道注册到选择器上，并且指定事件为：监听接收
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);

            //轮询获取选择器上已经准备就绪的事件
            //select() 是一个阻塞方法，在没有通道事件会等待，有的通道事件触发，就>0
            System.out.println("NIO服务端启动成功！");
            System.out.println("等待：");
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
                    } else if (sk.isReadable()) {
                        //可读准备就绪，开始读取数据
                        //参考：ChannelTest  read方法
                        SocketChannel sChannel = (SocketChannel) sk.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int len = 0;
                        while ((len = sChannel.read(buffer)) > 0) {
                            buffer.flip();
                            System.out.println("读取数据：" + new String(buffer.array(), 0, len));
                            buffer.clear();
                        }
                    }

                    //取消选择键
                    it.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
