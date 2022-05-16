package com.giousa.tools.design;

public class ProxyDesignTest {

    public static void main(String[] args) {
        ProxyMsgService proxyMsgService = new ProxyMsgService(new MsgServiceImpl());
        proxyMsgService.sendMsg("你好呀");
    }


}
