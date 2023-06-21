package com.giousa.tools.thread.pool;

public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPool.execute(() -> {
            System.out.println(getThreadName() + ": run!");
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(getThreadName()+": "+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ThreadPool.execute(() -> {
            System.out.println(getThreadName() + ": run!");
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(getThreadName()+": "+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ThreadPool.execute(() -> {
            System.out.println(getThreadName() + ": run!");
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(getThreadName()+": "+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }

}
