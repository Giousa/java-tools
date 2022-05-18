package com.giousa.tools.thread;

public class MainThreadTest {

    public static void main(String[] args) {
        ThreadTest t1 = new ThreadTest();
        ThreadTest t2 = new ThreadTest();
//        t1.run();
        t1.start();
        t2.start();
    }
}
