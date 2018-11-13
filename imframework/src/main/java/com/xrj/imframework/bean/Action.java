package com.xrj.imframework.bean;

/**
 * Created by xrj on 2018/5/16.
 */
public enum Action {
    LOGIN("login", 1, null),//授权握手包
    HEARTBEAT("heartbeat", 1, null),//心跳
    SYNC("sync", 1, null);//同步数据

    private String action;
    private int reqEvent;
    private Class respClazz;


    Action(String action, int reqEvent, Class respClazz) {
        this.action = action;
        this.reqEvent = reqEvent;
        this.respClazz = respClazz;
    }


    public String getAction() {
        return action;
    }


    public int getReqEvent() {
        return reqEvent;
    }


    public Class getRespClazz() {
        return respClazz;
    }
}
