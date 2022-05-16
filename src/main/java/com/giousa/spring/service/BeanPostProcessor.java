package com.giousa.spring.service;

/**
 * Bean对象的后置处理器，这里主要负责AOP
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInitialization(Object bean, String beanName);
}
