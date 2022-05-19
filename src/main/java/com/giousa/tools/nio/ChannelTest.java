package com.giousa.tools.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {

    public static void main(String[] args) {
//        write();
        read();
//        copyFile();
//        scatter();
//        transferFrom();
//        transferTo();
    }

    /**
     * 把原通道数据复制到目标通道
     */
    private static void transferTo() {
        try {

            //字节输入管道
            FileInputStream is = new FileInputStream("src/main/resources/nio_channel/data01.txt");
            FileChannel isChannel = is.getChannel();

            //字节输出管道
            FileOutputStream os = new FileOutputStream("src/main/resources/nio_channel/data04.txt");
            FileChannel osChannel = os.getChannel();

            isChannel.transferTo(isChannel.position(), isChannel.size(), osChannel);

            isChannel.close();
            osChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从目标通道中，复制原通道数据
     */
    private static void transferFrom() {
        try {

            //字节输入管道
            FileInputStream is = new FileInputStream("src/main/resources/nio_channel/data01.txt");
            FileChannel isChannel = is.getChannel();

            //字节输出管道
            FileOutputStream os = new FileOutputStream("src/main/resources/nio_channel/data03.txt");
            FileChannel osChannel = os.getChannel();

            osChannel.transferFrom(isChannel, isChannel.position(), isChannel.size());

            isChannel.close();
            osChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 分散和聚集
     */
    private static void scatter() {
        try {

            //字节输入管道
            FileInputStream is = new FileInputStream("src/main/resources/nio_channel/data01.txt");
            FileChannel isChannel = is.getChannel();

            //字节输出管道
            FileOutputStream os = new FileOutputStream("src/main/resources/nio_channel/data02.txt");
            FileChannel osChannel = os.getChannel();

            //定义多个缓冲区，做数据分散
            ByteBuffer buffer1 = ByteBuffer.allocate(4);
            ByteBuffer buffer2 = ByteBuffer.allocate(8);
            ByteBuffer buffer3 = ByteBuffer.allocate(1024);

            //放入数组
            ByteBuffer[] buffers = {buffer1, buffer2, buffer3};

            //从通道中读取数据，分散到不同缓冲区
            isChannel.read(buffers);

            //从每个缓冲区中，查看是否有数据
            for (ByteBuffer buffer : buffers) {
                //切换读模式
                buffer.flip();
                System.out.println("读取数据：");
                System.out.println(new String(buffer.array(), 0, buffer.remaining()));
            }

            //聚集，写入通道
            osChannel.write(buffers);

            isChannel.close();
            osChannel.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copyFile() {
        try {
            File srcFile = new File("src/main/resources/nio_channel/data01.txt");
            File destFile = new File("src/main/resources/nio_channel/data01_new.txt");

            //字节输入管道
            FileInputStream fis = new FileInputStream(srcFile);
            FileChannel isChannel = fis.getChannel();

            //字节输出管道
            FileOutputStream fos = new FileOutputStream(destFile);
            FileChannel osChannel = fos.getChannel();


            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                //先清空缓冲区
                buffer.clear();
                //读取数据
                int flag = isChannel.read(buffer);
                if (flag == -1) {
                    break;
                }
                //缓冲区切换可读模式
                buffer.flip();
                //写入
                osChannel.write(buffer);
            }

            isChannel.close();
            osChannel.close();
            System.out.println("复制完成！");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void read() {
        try {
            FileInputStream is = new FileInputStream("src/main/resources/nio_channel/data01.txt");

            FileChannel channel = is.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(10);

            //采用remaining方式，需要进行flip
//            channel.read(buffer);
//            System.out.println("读取数量："+channel.read(buffer));
//            buffer.flip();
//            String s = new String(buffer.array(), 0, buffer.remaining());
//            System.out.println("读取：" + s);

            System.out.println("开始读取：");
            StringBuffer sb = new StringBuffer();
            int len = 0;
//            while ((len = channel.read(buffer)) > 0) {
//                buffer.flip();
//                //注意：len不能少于缓冲区容量值，否则有可能读取的数据会少，这样结果就会缺失
//                String s = new String(buffer.array(), 0, len);
//                System.out.println("读取数据：" + s);
//                sb.append(s);
//                buffer.clear();
//            }

            while (true) {
                len = channel.read(buffer);
                System.out.println("读取数量：" + len);
                if (len < 0) {
                    break;
                }
                buffer.flip();
                //注意：len不能少于缓冲区容量值，否则有可能读取的数据会少，这样结果就会缺失
                String s = new String(buffer.array(), 0, len);
                System.out.println("读取数据：" + s);
                sb.append(s);
                buffer.clear();
            }

            System.out.println("最终结果：");
            System.out.println(sb.toString());

            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void write() {
        try {

            FileOutputStream os = new FileOutputStream("src/main/resources/nio_channel/data01.txt");

            FileChannel channel = os.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("不笑猫".getBytes());

            //调flip()方法将缓冲区改成读取模式，否则插入的都是NULL等乱码数据
            buffer.flip();

            //写入数据
            channel.write(buffer);

            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
