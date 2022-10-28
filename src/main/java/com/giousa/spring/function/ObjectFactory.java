package com.giousa.spring.function;

@FunctionalInterface
public interface ObjectFactory<T> {

    T getObject();

}