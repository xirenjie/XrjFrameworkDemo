package com.xrj.imframework.req;

import com.xrj.imframework.inter.ICallback;

/**
 * Created by Administrator on 2018/5/16.
 */
public class CallbackDataWrapper<T> {
    private ICallback<T> callback;
    private Object data;

    public CallbackDataWrapper(ICallback<T> callback, Object data) {
        this.callback = callback;
        this.data = data;
    }

    public ICallback<T> getCallback() {
        return callback;
    }


    public void setCallback(ICallback<T> callback) {
        this.callback = callback;
    }


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }
}
