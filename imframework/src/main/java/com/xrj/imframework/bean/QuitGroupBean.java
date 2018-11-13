package com.xrj.imframework.bean;

/**
 * Created by Administrator on 2018/9/10 0010.
 */

public class QuitGroupBean {
    /**
     * command : 10
     * data : {"group":"100","user":{"id":"xirenjie","nick":"何乔丹"}}
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
         * user : {"id":"xirenjie","nick":"何乔丹"}
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
             * id : xirenjie
             * nick : 何乔丹
             */

            private String id;
            private String nick;

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
        }
    }
}
