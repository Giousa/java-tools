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
     *
     * Tomcat 四大Servlet容器
     * engin Host Context Wrapper
     */
    public void start(String hostname, Integer port) {

        //启动Tomcat(底层是socket)
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);

        /**
         * 可以理解为一个Tomcat，一个Tomcat一个engine
         * 一个engin可以有多个Host
         * List<Host> hosts
         *
         *
         */
        StandardEngine engine = new StandardEngine();
        engine.setDefaultHost(hostname);

        /**
         * 一个Host表示一个虚拟服务器，可以给每个Host配置一个域名
         * 一个Host可以有多个Context
         * List<Context> contexts
         */
        StandardHost host = new StandardHost();
        host.setName(hostname);

        /**
         * 一个Context就是一个应用，一个项目
         * 一个Context可以有多个Wrapper实例对象
         * List<Wrapper> wrappers
         *
         */
        StandardContext context = new StandardContext();
        String contextPath = "";
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        /**
         * 一个Wrapper表示多个Servlet包装
         * List<Servlet> servlets
         */

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
