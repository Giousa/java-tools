package com.giousa.tools.thread;

public class ThreadTest extends Thread {

    @Override
    public void run() {
        System.out.println(getThreadName() + ": run!");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(getThreadName()+": "+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getThreadName() {
        return Thread.currentThread().getName();
    }
}
