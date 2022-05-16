package com.giousa.spring.service;

import com.giousa.spring.annotation.Component;

@Component("myBeanPostProcessorV2")
public class MyBeanPostProcessorV2 implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("MyBeanPostProcessorV2 before. bean = " + beanName + ",beanName = " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("MyBeanPostProcessorV2 after. bean = " + beanName + ",beanName = " + beanName);
        return bean;
    }
}
