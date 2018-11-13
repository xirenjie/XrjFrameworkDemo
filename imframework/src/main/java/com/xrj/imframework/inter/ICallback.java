package com.xrj.imframework.inter;

/**
 * Created by xrj on 2018/5/16.
 * ui层回调
 */
public interface ICallback<T> {
    void onSuccess(T t);

    void onFail(String msg);
}
