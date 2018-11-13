package com.xrjframework.demo.nice.presenter;

import android.content.Context;
import android.util.Log;

import com.xrj.framework.nice.presenter.BasePresenter;
import com.xrj.framework.nice.rxjava.RxManager;
import com.xrj.framework.nice.rxjava.RxSubscriber;
import com.xrjframework.demo.nice.bean.GankItemData;
import com.xrjframework.demo.nice.model.IGankItemModel;
import com.xrjframework.demo.nice.model.impl.GankItemModelImpl;
import com.xrjframework.demo.nice.view.GankItemView;

import java.util.List;

/**
 * Author: Othershe
 * Time: 2016/8/12 14:29
 */
public class GankItemPresenter extends BasePresenter<GankItemView> {
    private IGankItemModel mModel;

    public GankItemPresenter() {
        mModel = new GankItemModelImpl();
    }

    public void getGankItemData(String suburl,Context mcontext) {
        Log.i("xiren", suburl + "地址是");
        mSubscription = RxManager.getInstance().doSubscribe1(mModel.getGankItemData(suburl), new RxSubscriber<List<GankItemData>>(true,mcontext) {
            @Override
            protected void _onNext(List<GankItemData> gankItemData) {
                mView.onSuccess(gankItemData);
            }

            @Override
            protected void _onError() {
                mView.onError();
            }
        });
    }
}
