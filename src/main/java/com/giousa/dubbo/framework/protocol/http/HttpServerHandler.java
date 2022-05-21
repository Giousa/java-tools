package com.giousa.dubbo.framework.protocol.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServerHandler {

    /**
     * 处理请求
     * @param req
     * @param resp
     */
    public void handler(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //解析请求--调用哪个服务--哪个方法--参数类型列表--具体参数等等
        resp.getWriter().println("hello .this is test");
    }
}
