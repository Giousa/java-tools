package com.giousa.spring.service;

import com.giousa.spring.annotation.Component;

@Component("myBeanPostProcessor")
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("MyBeanPostProcessor before. bean = " + beanName + ",beanName = " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("MyBeanPostProcessor after. bean = " + beanName + ",beanName = " + beanName);
        return bean;
    }
}
