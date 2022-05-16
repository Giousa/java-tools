package com.giousa.tools.design.proxy;

public class MsgServiceImpl implements MsgService {
    @Override
    public void sendMsg(String msg) {
        System.out.println("MsgServiceImpl send msg : " + msg);
    }
}
