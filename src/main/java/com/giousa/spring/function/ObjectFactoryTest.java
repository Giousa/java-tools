package com.giousa.spring.function;

public class ObjectFactoryTest {

    public static void main(String[] args) {
        Object obj = getSingleton("实例测试", () -> {
            System.out.println("在执行泛型式方法前...");
            return "你好，这个是泛型式接口的测试用例";
        });

        System.out.println("最终结果： " + obj);
    }

    public static Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {

        System.out.println("beanName： " + beanName);
        System.out.println("singletonFactory： " + singletonFactory);

        Object object = singletonFactory.getObject();
        System.out.println("获取的object： " + object);

        return object;
    }
}
