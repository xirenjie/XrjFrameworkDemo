package com.xrj.imframework.inter;

import com.xrj.imframework.bean.Action;
import com.xrj.imframework.req.Request;

/**
 * Created by xrj on 2018/5/16.
 */
public interface IWsCallback<T> {
    void onSuccess(T t);
    void onError(String msg, Request request, Action action);
    void onTimeout(Request request, Action action);
}
