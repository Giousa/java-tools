package com.giousa.tools.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SelectorClientTest {

    public static void main(String[] args) {
        try {
            SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
            System.out.println("NIO客户端启动成功！");

            sChannel.configureBlocking(false);

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            Scanner scanner = new Scanner(System.in);

            System.out.println("准备写入数据：");
            while (scanner.hasNext()) {
                String msg = scanner.nextLine();
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
                buffer.put(("日期【" + time + "】 msg: " + msg).getBytes());
                buffer.flip();
                sChannel.write(buffer);
                buffer.clear();
            }

            sChannel.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
