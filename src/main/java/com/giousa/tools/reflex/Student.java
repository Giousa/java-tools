package com.giousa.tools.reflex;

public class Student {

    static {
        System.out.println("Student 执行了【静态代码块】");
    }

    //静态变量
    private static String staticMethod = getStaticMethod();

    public static String getStaticMethod(){
        System.out.println("Student 执行【静态方法】");
        return "这个是静态方法";
    }

    public String name;

    private Integer age;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private Student(String name) {
        this.name = name;
    }

    private Student(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    private Integer getAge() {
        return age;
    }

    private String show(String msg) {
        return "展示了:" + msg;
    }
}
