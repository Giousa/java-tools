package com.giousa.dubbo.provider;

import com.giousa.dubbo.framework.protocol.dubbo.DubboServer;
import com.giousa.dubbo.framework.protocol.http.HttpServer;

public class Provider {

    public static void main(String[] args) {

        //启动tomcat、jetty
        HttpServer httpServer = new HttpServer();
        httpServer.start();

        //启动netty
        DubboServer dubboServer = new DubboServer();
        dubboServer.start();

    }
}
