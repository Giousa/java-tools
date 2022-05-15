package com.giousa.tools.reflex;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflexTest {

    /**
     * 获取反射对象
     * 1、对象名.getClass()  这种使用较少
     * 2、类名.class
     * 3、Class.forName("完整的包名+类名") 比较常见
     */
    public static void getClassTest() throws ClassNotFoundException {
        Student student = new Student();
        Class clazz1 = student.getClass();
        System.out.println("clazz1 = " + clazz1);

        Class clazz2 = Student.class;
        System.out.println("clazz2 = " + clazz2);

        Class clazz3 = Class.forName("com.giousa.tools.reflex.Student");
        System.out.println("clazz3 = " + clazz3);
    }

    /**
     * 获取构造方法
     * public Constructor<?>[] getConstructors()	                        获取所有的公有的构造方法
     * public Constructor<?>[] getDeclaredConstructors()	                获取所有的构造方法(包括私有的)
     * public Constructor getConstructor(Class<?>… parameterTypes)	        获取单个公有的构造方法
     * public Constructor getDeclaredConstructor(Class<?>… parameterTypes)	获取单个私有的构造方法
     */
    public static void getConstructorTest() throws Exception {
        Class clazz = Class.forName("com.giousa.tools.reflex.Student");
        System.out.println("类名：" + clazz);
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("所有公共的构造方法：" + constructor);
        }

        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            System.out.println("所有公共+私有的构造方法：" + constructor);
        }

        Constructor constructor = clazz.getConstructor(String.class, Integer.class);
        System.out.println("获取单个公共构造方法： " + constructor);

        Constructor declaredConstructor = clazz.getDeclaredConstructor(Integer.class);
        System.out.println("获取单个构造方法： " + declaredConstructor);

    }

    /**
     * 获取成员方法
     * public Method[] getMethods()	                                            获取所有的公有的方法(包括父类公有的方法)
     * public Method[] getDeclaredMethods()	                                    获取所有的方法(包括私有)
     * public Method getMethod(String name, Class<?>… parameterTypes)	        获取单个公有的方法
     * public Method getDeclaredMethod(String name, Class<?>… parameterTypes)	获取单个公有或私有的方法
     */
    public static void getMethodTest() throws Exception {

        Class clazz = Class.forName("com.giousa.tools.reflex.Student");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println("获取所有公有方法: " + method);
        }

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println("获取所有公有+私有方法: " + method);
        }

        Method getPubMethod = clazz.getMethod("getName");
        System.out.println("获取单个公共的方法： " + getPubMethod);

        Method getPriMethod = clazz.getDeclaredMethod("getAge");
        System.out.println("获取单个公有或私有的方法： " + getPriMethod);

        Constructor declaredConstructor = clazz.getDeclaredConstructor(String.class, Integer.class);
        Student student = (Student) declaredConstructor.newInstance("不笑猫", 12);
        String name = student.getName();
        System.out.println("获取姓名: " + name);
        //私有方法，需要添加访问控制检查，共有方法不需要
        getPriMethod.setAccessible(true);
        Integer age = (Integer) getPriMethod.invoke(student);
        System.out.println("获取年龄: " + age);

    }


    /**
     * 获取成员变量
     * public Field[] getFields()	                获取公有的成员变量
     * public Field[] getDeclaredFields()	        获取所有的成员变量
     * public Field getField(String name)	        获取单个成员变量
     * public Field getDeclaredField(String name)	获取私有的成员变量
     */
    public static void getFiledTest() throws Exception {
        Class clazz = Class.forName("com.giousa.tools.reflex.Student");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("获取所有的成员变量：" + field);
        }

        Field pubField = clazz.getDeclaredField("name");
        Field priField = clazz.getDeclaredField("age");
        System.out.println("获取共有或私有的成员变量：" + pubField);
        System.out.println("获取共有或私有的成员变量：" + priField);
    }

    public static void main(String[] args) throws Exception {
//        getClassTest();
//        getConstructorTest();
//        getMethodTest();
        getFiledTest();
    }
}
