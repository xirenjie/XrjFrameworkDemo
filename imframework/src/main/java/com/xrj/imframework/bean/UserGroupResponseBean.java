package com.xrj.imframework.bean;

/**
 * Created by 袭人杰 on 2018/5/25.
 * 用户进群通知
 */
public class UserGroupResponseBean {


    /**
     * command : 9
     * data : {"group":"100","user":{"avatar":"http://img4.duitang.com/uploads/item/201410/03/20141003160129_nUfjf.thumb.700_0.jpeg","id":"33","leave":"彩民3","nick":"袭大大","terminal":"ws","total_beans":895}}
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
         * group : 100
         * user : {"avatar":"http://img4.duitang.com/uploads/item/201410/03/20141003160129_nUfjf.thumb.700_0.jpeg","id":"33","leave":"彩民3","nick":"袭大大","terminal":"ws","total_beans":895}
         */

        private String group;
        private UserBean user;

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * avatar : http://img4.duitang.com/uploads/item/201410/03/20141003160129_nUfjf.thumb.700_0.jpeg
             * id : 33
             * leave : 彩民3
             * nick : 袭大大
             * terminal : ws
             * total_beans : 895
             */

            private String avatar;
            private String id;
            private String leave;
            private String nick;
            private String terminal;
            private int total_beans;



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

            public String getLeave() {
                return leave;
            }

            public void setLeave(String leave) {
                this.leave = leave;
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

            public int getTotal_beans() {
                return total_beans;
            }

            public void setTotal_beans(int total_beans) {
                this.total_beans = total_beans;
            }
        }
    }
}
