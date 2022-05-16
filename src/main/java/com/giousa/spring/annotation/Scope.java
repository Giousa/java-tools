package com.giousa.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Scope {

    /**
     * prototype: 每次获取都生成新的
     * singleton: 单例
     */
    String value() default "singleton";
}
