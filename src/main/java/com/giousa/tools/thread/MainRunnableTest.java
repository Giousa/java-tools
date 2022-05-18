package com.giousa.tools.thread;

public class MainRunnableTest {

    private static void test(){
        Runnable r3 = () -> System.out.println("当前线程3："+Thread.currentThread().getName());
//        r3.run();

        Runnable r4 = () -> System.out.println("当前线程4："+Thread.currentThread().getName());
//        r4.run();

        new Thread(r3).start();
        new Thread(r4).start();

        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("当前线程5："+threadName+",启动成功！");
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(threadName+ " : "+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("当前线程6："+threadName+",启动成功！");
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    System.out.println(threadName+ " : "+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    public static void main(String[] args){
        RunnableTest r1 = new RunnableTest("线程-中文版");
        RunnableTest r2 = new RunnableTest("thread-other");

        r1.start();
//        r2.start();
//        r2.start();
//        r1.start();

//        r1.run();
//        r2.run();
    }
}
