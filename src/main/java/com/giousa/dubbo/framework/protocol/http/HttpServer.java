package com.giousa.dubbo.framework.protocol.http;

import org.apache.catalina.startup.Tomcat;

public class HttpServer {

    /**
     * 启动Tomcat、Jetty服务器，可以接收http协议
     */
    public void start(){

        //启动Tomcat(底层是socket)
        Tomcat tomcat = new Tomcat();
        

    }
}
