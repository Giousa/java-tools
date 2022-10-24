package com.giousa.tools.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 加载器介绍
 * 启动类加载器Bootstrap ClassLoader:加载JRE_HOME/lib下的核心包，该类加载器是用c++写的。
 * 扩展类加载器Extension ClassLoader:加载JRE_HOME/lib/ext目录下的扩展包,也可以通过启动参数-Djava.ext.dirs指定，该类用java编写。对应ExtClassLoader类
 * 应用类加载器Application ClassLoader:应用类加载器，加载classpath下的字节码文件，用java编写，对应AppClassLoader这个类，可以通过ClassLoader类的静态方法getSystemClassLoader()获得，所以又叫系统类加载器
 * 自定义类加载器：自定义的类加载器，通过直接或者间接继承抽象的ClassLoader类。
 */
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
