package com.giousa.tools.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ClassLoadTest {

    private static final String className = "com.giousa.tools.reflex.Student";

    public static void main(String[] args) throws Exception {
        System.out.println("-----------Class.forName-------------");
        classForTest();
        System.out.println("-----------ClassLoad-------------");
        classLoadTest();
    }

    public static void classForTest() throws Exception {
        Class clazz = Class.forName(className);

        Constructor declaredConstructor = clazz.getDeclaredConstructor(String.class, Integer.class);
        Student student = (Student) declaredConstructor.newInstance("不笑猫", 12);
        String name = student.getName();
        System.out.println("获取姓名: " + name);

        Method getPriMethod = clazz.getDeclaredMethod("getAge");
        System.out.println("获取单个公有或私有的方法： " + getPriMethod);
        //私有方法，需要添加访问控制检查，共有方法不需要
        getPriMethod.setAccessible(true);
        Integer age = (Integer) getPriMethod.invoke(student);
        System.out.println("获取年龄: " + age);
    }

    public static void classLoadTest() throws Exception {
        Class clazz = ClassLoader.getSystemClassLoader().loadClass(className);
        Constructor declaredConstructor = clazz.getDeclaredConstructor(String.class, Integer.class);
        Student student = (Student) declaredConstructor.newInstance("不笑猫", 12);
        String name = student.getName();
        System.out.println("获取姓名: " + name);

        Method getPriMethod = clazz.getDeclaredMethod("getAge");
        System.out.println("获取单个公有或私有的方法： " + getPriMethod);
        //私有方法，需要添加访问控制检查，共有方法不需要
        getPriMethod.setAccessible(true);
        Integer age = (Integer) getPriMethod.invoke(student);
        System.out.println("获取年龄: " + age);

    }
}
