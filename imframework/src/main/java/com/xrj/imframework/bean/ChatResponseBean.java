package com.xrj.imframework.bean;

/**
 * Created by 袭人杰 on 2018/5/25.
 * 接收私聊消息
 */
public class ChatResponseBean {

    /**
     * command : 11
     * data : {"chatType":2,"cmd":11,"content":"12","createTime":1527230248092,"from":"哈哈哈","id":"e5d898fd55ea4470a8d95c51199c7d64","msgType":0,"to":"xirenjie"}
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
         * chatType : 2
         * cmd : 11
         * content : 12
         * createTime : 1527230248092
         * from : 哈哈哈
         * id : e5d898fd55ea4470a8d95c51199c7d64
         * msgType : 0
         * to : xirenjie
         */

        private int chatType;
        private int cmd;
        private String content;
        private long createTime;
        private String from;
        private String id;
        private int msgType;
        private String to;

        public int getChatType() {
            return chatType;
        }

        public void setChatType(int chatType) {
            this.chatType = chatType;
        }

        public int getCmd() {
            return cmd;
        }

        public void setCmd(int cmd) {
            this.cmd = cmd;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getMsgType() {
            return msgType;
        }

        public void setMsgType(int msgType) {
            this.msgType = msgType;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }
}
