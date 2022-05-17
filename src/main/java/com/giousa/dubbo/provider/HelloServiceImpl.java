package com.giousa.dubbo.provider;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String userName) {
        System.out.println("Hello: " + userName);
        return "Hello: " + userName;
    }
}
