package com.xrj.imframework.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/10 0010.
 */

public class OnLineUserBean {
    /**
     * code : 10005
     * command : 18
     * data : [{"avatar":"http://images.rednet.cn/articleimage/2013/01/23/1403536948.jpg","id":"1536549815373","nick":"吴娜","terminal":"ws"},{"avatar":"http://images.rednet.cn/articleimage/2013/01/23/1403536948.jpg","id":"xirenjie","nick":"何乔丹","terminal":"ws"},{"avatar":"http://images.rednet.cn/articleimage/2013/01/23/1403536948.jpg","id":"xirenjie","nick":"何乔丹","terminal":"ws"},{"avatar":"http://images.rednet.cn/articleimage/2013/01/23/1403536948.jpg","id":"xirenjie","nick":"何乔丹","terminal":"ws"}]
     * msg : 获取在线用户信息成功!
     */

    private int code;
    private int command;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * avatar : http://images.rednet.cn/articleimage/2013/01/23/1403536948.jpg
         * id : 1536549815373
         * nick : 吴娜
         * terminal : ws
         */

        private String avatar;
        private String id;
        private String nick;
        private String terminal;
        private String leave;
        private int total_beans;

        public String getLeave() {
            return leave;
        }

        public void setLeave(String leave) {
            this.leave = leave;
        }

        public int getTotal_beans() {
            return total_beans;
        }

        public void setTotal_beans(int total_beans) {
            this.total_beans = total_beans;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getTerminal() {
            return terminal;
        }

        public void setTerminal(String terminal) {
            this.terminal = terminal;
        }
    }
}
