package com.giousa.tools.classload;

import java.io.*;

public class MyClassLoader extends ClassLoader {

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * 重写loadClass，打破双亲委派
     *
     * ClassLoader classLoader = getSystemClassLoader().getParent();
     * 我们一开始加载loadClass类的时候，使用加载器的父类，也就是extClassLoad去加载
     * 倘若加载成功，直接返回
     * 加载失败，直接调用findClass，使用我们重写的方式去加载。
     *
     * 原因：
     * 任何一个类，都默认继承Object类，是Object的子类。
     * 一旦加载类，也会默认加载它的父类Object。
     * 但是显而易见的，我们自定义的加载器，是无法加载Object的，会报异常，因为这个类是java.开头的。
     *
     * 查询bug位置：findClass下，执行defineClass的时候，在preDefineClass方法里面出错
     */
    @Override
    public Class<?> loadClass(String name) {
        System.out.println("loadClass. name: " + name);

        //使用extClassLoad
        ClassLoader classLoader = getSystemClassLoader().getParent();

        Class<?> clazz = null;
        try {
            clazz = classLoader.loadClass(name);
        } catch (Exception e) {
            System.out.println("loadClass fail. name: " + name);
            e.printStackTrace();
        }

        if (clazz == null) {
            clazz = findClass(name);
        }

        System.out.println("最终查询出的class. clazz = " + clazz.getName());
        return clazz;
    }

    /**
     * 配合打破双亲委派
     */
    @Override
    protected Class<?> findClass(String name) {
        System.out.println("findClass. name: " + name);
        InputStream is = null;
        byte[] data = null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            is = new FileInputStream(new File("target/classes/com/giousa/tools/classload/StudentInfo.class"));

            int c = 0;
            while ((c = is.read()) != -1) {
                baos.write(c);
            }

            data = baos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return this.defineClass(name, data, 0, data.length);
    }

    public static void main(String[] args) throws Exception {

        test01();


    }

    /**
     * 没有打破双亲委派机制，因为MyClassLoader的父类是AppClassLoader
     * 需要重写loadClass才可以
     */
    private static void test01() throws Exception {
        System.out.println("MyClassLoader.class.getClassLoader() = " + MyClassLoader.class.getClassLoader());

        MyClassLoader myClassLoader = new MyClassLoader(MyClassLoader.class.getClassLoader());

        Class<?> clazz = myClassLoader.loadClass("com.giousa.tools.classload.StudentInfo");

        //因为MyClassLoader的父类是AppClassLoader
        System.out.println("加载方式：" + clazz.getClassLoader());//AppClassLoader
    }
}
