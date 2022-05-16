package com.giousa.spring.config;

import com.giousa.spring.annotation.ComponentScan;

/**
 * 目前是做的简陋版，所有服务需要在此文件夹下，否则扫描不到
 */
@ComponentScan("com.giousa.spring.service")
public class AppConfig {
}
