package com.xrj.imframework.bean;

/**
 * Created by 袭人杰 on 2018/5/25.
 */
public class AuthResponseBean {

    /**
     * code : 10009
     * command : 4
     * data : {"token":"校验码wchao"}
     * msg : 鉴权成功!
     */

    private int code;
    private int command;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * token : 校验码wchao
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
