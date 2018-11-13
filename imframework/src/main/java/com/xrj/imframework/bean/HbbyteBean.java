package com.xrj.imframework.bean;

/**
 * Created by 袭人杰 on 2018/5/25.
 * 被动接收心跳包
 */
public class HbbyteBean {

    /**
     * command : 13
     * data : {"hbbyte":-128}
     */

    private int command;
    private DataBean data;

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

    public static class DataBean {
        /**
         * hbbyte : -128
         */

        private int hbbyte;

        public int getHbbyte() {
            return hbbyte;
        }

        public void setHbbyte(int hbbyte) {
            this.hbbyte = hbbyte;
        }
    }
}
