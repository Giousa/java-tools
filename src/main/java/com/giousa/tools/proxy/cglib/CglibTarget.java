package com.giousa.tools.proxy.cglib;

/**
 * 原始目标方法
 */
public class CglibTarget {

    public void save() {
        System.out.println("save()");
    }

    public void save(int i) {
        System.out.println("save(int)");
    }

    public void save(long i) {
        System.out.println("save(long)");
    }

}
