package com.giousa.tools.design;

public class ProxyMsgService implements MsgService {

    private MsgService msgService;

    public ProxyMsgService(MsgService msgService) {
        this.msgService = msgService;
    }

    @Override
    public void sendMsg(String msg) {
        before();
        msgService.sendMsg(msg);
        after();
    }

    private void before() {
        System.out.println("执行前");
    }

    private void after() {
        System.out.println("执行后");
    }
}
