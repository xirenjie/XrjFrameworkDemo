package com.xrj.framework.nice.net;

/**
 * Created by a on 2017/6/16.
 *
 * @auther XRJ
 */
public class ApiException extends RuntimeException {
    public ApiException() {
        this("服务器异常...");
    }

    public ApiException(String message) {
        super(message);
    }
}
