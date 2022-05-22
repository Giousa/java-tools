package com.giousa.tools.classload;


/**
 * 类加载器
 * <p>
 * 引导类加载器
 * BootstrapClassLoad  加载class的目录：jre/lib
 * ↑
 * parent
 * ↑
 * 拓展类加载器
 * ExtClassLoad        加载class的目录：jre/ext/lib
 * ↑
 * parent
 * ↑
 * 系统类加载器
 * AppClassLoad        加载class的目录：classpath
 */
public class ClassLoadTest {

    public static void main(String[] args) throws Exception {

        /**
         * 注意：getSystemClassLoader系统类是无法打破双亲委派机制的，需要自定义一个ClassLoad
         */
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();//AppClassLoad

        Class<?> clazz = classLoader.loadClass("com.giousa.tools.classload.StudentInfo");

        /**
         * 因为AppClassLoad先去让父类ExtClassLoad去加载StudentInfo类
         * 父类ExtClassLoad 找不到父类的情况下，优先让BootstrapClassLoad去加载
         * 最终，因为都加载不到StudentInfo，最后还是AppClassLoad来加载
         *
         * 这就是所谓的双亲委派机制:优先让父类先加载
         */
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("加载方式：" + clazz.getClassLoader());//AppClassLoad
        System.out.println("加载方式-父类：" + clazz.getClassLoader().getParent());//ExtClassLoad
        //永远为null，因为ExtClassLoad拿不到JVM层面的BootstrapClassLoad
        System.out.println("加载方式-父类的父类：" + clazz.getClassLoader().getParent().getParent());

        //返回正在运行的Java虚拟机的高分辨率时间源的当前值，以纳秒为单位。
        //System.nanoTime()的性能不如System.currentTimeMillis()
        System.out.println("System.nanoTime() = " + System.nanoTime());

    }
}
