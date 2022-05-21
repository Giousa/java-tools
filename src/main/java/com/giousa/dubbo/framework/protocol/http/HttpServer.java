package com.giousa.dubbo.framework.protocol.http;

import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class HttpServer {

    /**
     * 启动Tomcat、Jetty服务器，可以接收http协议
     */
    public void start(String hostname, Integer port) {

        //启动Tomcat(底层是socket)
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);

        StandardEngine engine = new StandardEngine();
        engine.setDefaultHost(hostname);


        StandardHost host = new StandardHost();
        host.setName(hostname);

        String contextPath = "";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);


        //下面两行不填，也可以正常启动Tomcat，只是运行的是一个空的容器
        //当自定义dispatcher后，网络请求：http://localhost:9090/ 就可以拦截转发
        tomcat.addServlet(contextPath,"dispatcher",new DispatcherServlet());
        context.addServletMappingDecoded("/*","dispatcher");

        try {
            tomcat.start();
            System.out.println("Tomcat 启动成功！");
            tomcat.getServer().await();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Tomcat 断开！");
        }
    }
}
