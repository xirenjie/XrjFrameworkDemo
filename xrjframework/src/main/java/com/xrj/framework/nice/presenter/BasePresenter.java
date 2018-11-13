package com.xrj.framework.nice.presenter;


import rx.Subscription;

/**
 * Created by a on 2017/6/16.
 *
 * @auther XRJ
 */
public class BasePresenter<V> {
    public V mView;
    protected Subscription mSubscription;

    public void attach(V view) {
        mView = view;
    }

    public void detach() {
        mView = null;
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
