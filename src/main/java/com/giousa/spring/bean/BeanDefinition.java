package com.giousa.spring.bean;

import lombok.Data;

@Data
public class BeanDefinition {

    /**
     * 类型
     */
    private Class clazz;

    /**
     * 作用域
     */
    private String scope;

}
