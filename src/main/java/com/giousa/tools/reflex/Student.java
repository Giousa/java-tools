package com.giousa.tools.reflex;

public class Student {

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
