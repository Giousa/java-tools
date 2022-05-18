package com.giousa.tools.thread;

import java.util.Objects;

/**
 * 创建线程方式一：实现Runnable，执行run
 */
public class RunnableTest implements Runnable {

    private String threadName;

    private Thread thread;

    public RunnableTest(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println(threadName + ":run");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
                if(i == 4){
                    //更改线程名称
                    thread.setName(threadName+"[修改]");
                }

                if(i == 7){
                    //终止线程
                    thread.interrupt();
                }

                System.out.println(threadName + ":打印  " + i+",实际线程:"+Thread.currentThread().getName()+",是否活跃:"+thread.isAlive());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        if (Objects.nonNull(thread)) {
            System.out.println(threadName + ":线程已启动！");
            return;
        }
        thread = new Thread(this, threadName);
        thread.start();
        System.out.println(threadName + ":线程启动成功！");
    }

}
