package com.giousa.tools.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Scanner;

public class NIOChatClient {


    //选择器
    private Selector selector;

    //客户端通道
    private SocketChannel sChannel;

    private static final int PORT = 9999;

    public NIOChatClient() {
        try {
            selector = Selector.open();

            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", PORT));

            sChannel.configureBlocking(false);

            sChannel.register(selector, SelectionKey.OP_READ);

            System.out.println("NIO 客户端启动成功！"+sChannel.getRemoteAddress());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NIOChatClient client = new NIOChatClient();

        new Thread(client::readInfo).start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("准备写入数据：");
        while (scanner.hasNext()) {
            String msg = scanner.nextLine();
            client.sendToServer(msg);
        }
    }

    private void sendToServer(String msg) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            buffer.put(("日期【" + time + "】 msg: " + msg).getBytes());
            buffer.flip();
            sChannel.write(buffer);
            buffer.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readInfo() {
        try {
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey sk = iterator.next();
                    if (sk.isReadable()) {
                        readData(sk);
                    }
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readData(SelectionKey sk) {
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
            System.out.println("客户端接收数据：" + sb.toString());
        } catch (Exception e) {
            try {
                System.out.println("f断开！ " + sChannel.getRemoteAddress());
                sk.cancel();
                sChannel.close();
            } catch (Exception exception) {
                e.printStackTrace();
            }
        }
    }
}
