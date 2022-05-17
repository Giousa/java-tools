package com.giousa.dubbo.consumer;

import com.giousa.dubbo.provider.HelloService;
import com.giousa.dubbo.provider.HelloServiceImpl;

public class Consumer {

    public static void main(String[] args) {

//        HelloService helloService = createProxy(HelloService.class);
        HelloService helloService = new HelloServiceImpl();
        helloService.sayHello("曹操");
    }
}
