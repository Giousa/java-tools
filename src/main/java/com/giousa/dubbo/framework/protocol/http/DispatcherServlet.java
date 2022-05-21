package com.giousa.dubbo.framework.protocol.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 所有请求共用一个Servlet实例，线程不安全，推荐无状态进行处理
 * 无状态：类下没有任何成员变量，不会涉及到变量的修订
 * 有状态：类下存在成员变量，每次请求对变量进行修改
 *
 *
 * 倘若每个请求单独一个Servlet实例，那么就是线程安全的
 * 需要implements SingleThreadModel
 */
public class DispatcherServlet extends HttpServlet {

    /**
     * 成员变量，不推荐使用
     */
//    private Long index;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("自定义DispatcherServlet");
        System.out.println("DispatcherServlet = "+this.toString());
//        super.service(req, resp);

        new HttpServerHandler().handler(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("hello .this is doGet");
        System.out.println("doGet req = "+ req);
        System.out.println("doGet resp = "+resp);
    }
}
