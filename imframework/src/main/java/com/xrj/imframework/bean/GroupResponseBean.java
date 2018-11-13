package com.xrj.imframework.bean;

/**
 * Created by 袭人杰 on 2018/5/24.
 * 接收群组消息
 */
public class GroupResponseBean {


    /**
     * command : 11
     * data : {"chatType":1,"cmd":11,"content":"2131492895","createTime":1540951598185,"from":"18764117706","group_id":"100","id":"8ac806f507684faab7fa88bd3874e38c","imgUrl":"哈哈","leave":"这是等级","msgType":2}
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
         * chatType : 1
         * cmd : 11
         * content : 2131492895
         * createTime : 1540951598185
         * from : 18764117706
         * group_id : 100
         * id : 8ac806f507684faab7fa88bd3874e38c
         * imgUrl : 哈哈
         * leave : 这是等级
         * msgType : 2
         */

        private int chatType;
        private int cmd;
        private String content;
        private long createTime;
        private String from;
        private String group_id;
        private String id;
        private String imgUrl;
        private String leave;
        private int msgType;
        private String nickName;


        public String getNickname() {
            return nickName;
        }

        public void setNickname(String nickName) {
            this.nickName = nickName;
        }

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

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLeave() {
            return leave;
        }

        public void setLeave(String leave) {
            this.leave = leave;
        }

        public int getMsgType() {
            return msgType;
        }

        public void setMsgType(int msgType) {
            this.msgType = msgType;
        }
    }
}
