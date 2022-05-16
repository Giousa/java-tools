package com.giousa.spring;

import com.giousa.spring.config.AppConfig;
import com.giousa.spring.service.UserService;

public class SpringMainTest {

    public static void main(String[] args) {
        CustomApplicationContext customApplicationContext = new CustomApplicationContext(AppConfig.class);
        UserService userService1 = (UserService) customApplicationContext.getBean("userService");
        UserService userService2 = (UserService) customApplicationContext.getBean("userService");
        UserService userService3 = (UserService) customApplicationContext.getBean("userService");
        System.out.println(userService1);
        System.out.println(userService2);
        System.out.println(userService3);

        userService1.printfOrder();
        userService2.printfOrder();
        userService3.printfOrder();

        System.out.println("defaultUser: "+userService1.getDefaultUser());
        System.out.println("defaultUser: "+userService2.getDefaultUser());
        System.out.println("defaultUser: "+userService3.getDefaultUser());
    }
}
