package com.xrjframework.demo.video;

/**
 * Created by Administrator on 2018/6/22 0022.
 */
public class LoginTokenBean {

    /**
     * status : 200
     * data : eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJJZCI6IjEiLCJuYW1lIjoiTXIuQUciLCJleHAiOjE1Mjk2NDcyNjV9.k9JVFIfQSL0FCA6BO2qjl8c7kMpTcr6ZCMggGXs1-l_u3eooqZWWMx9L-Yi8BFhMm5-fFFMHZfHMTv_k9YcjVSk9Vzaj-J_hZpy46BJxLuan_-GPowhFSSqUPd8BLokNoTeeBVhmBQ7EwkCeW2AlsokE0Q7M7pRPrqFOiqlU9oU
     * rel : false
     */

    private int status;
    private String data;
    private boolean rel;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }
}
