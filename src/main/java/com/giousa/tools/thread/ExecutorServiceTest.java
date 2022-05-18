package com.giousa.tools.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int num = 0; num < 5; num++) {
            System.out.println("num = " + num);
            int finalNum = num;
            executorService.execute(() -> {
                for (int i = 0; i < 2; i++) {
                    try {
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName() + ": i=" + i + ",num=" + finalNum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
