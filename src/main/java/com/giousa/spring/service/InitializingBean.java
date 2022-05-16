package com.giousa.spring.service;

public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
