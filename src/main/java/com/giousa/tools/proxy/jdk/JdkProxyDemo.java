package com.giousa.tools.proxy.jdk;

import com.giousa.tools.proxy.ArtServiceImpl;
import com.giousa.tools.proxy.IArtService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyDemo {

    public static void main(String[] args) {
        IArtService artService = new ArtServiceImpl();

        ClassLoader classLoader = JdkProxyDemo.class.getClassLoader();
        IArtService proxy = (IArtService) Proxy.newProxyInstance(classLoader, new Class[]{IArtService.class}, new InvocationHandler() {
            //proxy 代理对象自身
            //method 正在执行的方法
            //方法参数
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                Object result = method.invoke(artService, args);
                //代理类返回的是目标方法执行的结果
                System.out.println("after");
                return result;
            }
        });
        proxy.work("张三");
    }
}
