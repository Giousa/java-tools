package com.giousa.tools.nio;

import java.nio.ByteBuffer;

/**
 * 1.capacity
 * 作为一个内存块，Buffer有一个固定的大小值，也叫“capacity”.你只能往里写capacity个byte、long，char等类型。一旦Buffer满了，需要将其清空（通过读数据或者清除数据）才能继续写数据往里写数据。
 * 2.position
 * 当你写数据到Buffer中时，position表示当前的位置。初始的position值为0.当一个byte、long等数据写到Buffer后， position会向前移动到下一个可插入数据的Buffer单元。position最大可为capacity.
 * 当读取数据时，也是从某个特定位置读。当将Buffer从写模式切换到读模式，position会被重置为0. 当从Buffer的position处读取数据时，position向前移动到下一个可读的位置。
 * 3.limit
 * 在写模式下，Buffer的limit表示你最多能往Buffer里写多少数据。 写模式下，limit等于Buffer的capacity。
 * 当切换Buffer到读模式时， limit表示你最多能读到多少数据。因此，当切换Buffer到读模式时，limit会被设置成写模式下的position值。换句话说，你能读到之前写入的所有数据（limit被设置成已写数据的数量，这个值在写模式下就是position）
 */
public class BufferTest {


    public static void main(String[] args) {
        test4();
    }

    /**
     * 直接缓冲区[数据很大，生命周期很长，频繁操作，适合使用直接缓冲区]
     * 本地IO---直接内存（非堆内存）---非直接内存（堆内存）---直接内存---本地IO
     * 非直接缓冲区
     * 本地IO---直接内存---本地IO
     */
    private static void test4() {
        //直接缓冲区 - 更耗内存
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println("是否直接缓冲区allocateDirect: " + buffer.isDirect());

        //非直接缓冲区
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        System.out.println("是否直接缓冲区allocate: " + buffer2.isDirect());
    }

    private static void test3() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");

        buffer.put("real".getBytes());
        buffer.flip();

        byte[] bytes = new byte[2];
        buffer.get(bytes);
        System.out.println(new String(bytes));
        System.out.println(buffer.position());//2
        System.out.println(buffer.limit());//4
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");
    }

    public static void test2() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");

        String name = "real";
        buffer.put(name.getBytes());
        System.out.println(buffer.position());//4
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");

        buffer.clear();
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");
        System.out.println((char) buffer.get());

        byte[] bytes = new byte[2];
        buffer.get(bytes);
        System.out.println(new String(bytes));
    }


    /**
     * 查询缓冲区的位置、限制、容量等
     */
    public static void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");

        //一个汉字，占用3个字节
//        String name = "不笑猫";
        String name = "real";
        buffer.put(name.getBytes());
        System.out.println(buffer.position());//4
        System.out.println(buffer.limit());//10
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");

        buffer.flip();
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//4
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");

        char c = (char) buffer.get();
        System.out.println("读取字节：" + c);//h
        System.out.println(buffer.position());//1
        System.out.println(buffer.limit());//4
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");
    }

}
