package com.xrj.imframework.bean;

/**
 * Created by 袭人杰 on 2018/5/25.
 * 消息发送结果
 */
public class MessageResponseBean {

    /**
     * code : 10000
     * command : 12
     * msg : 发送成功
     */

    private int code;
    private int command;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
