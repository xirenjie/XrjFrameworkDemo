package com.xrj.imframework.bean;

import java.io.Serializable;

/**
 * Created by 袭人杰 on 2018/5/25.
 * 发送群组消息
 */
public class GroupSendBean implements Serializable{

    /**
     * from : xirenjie
     * createTime : 12321312312
     * cmd : 11
     * group_id : 100
     * chatType : 1
     * msgType : 0
     * content : 哈哈哈
     */

    private String from;
    private String createTime;
    private int cmd;
    private String group_id;
    private String chatType;
    private String msgType;
    private String content;
    private String imgUrl;
    private String leave;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
