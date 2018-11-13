package com.xrjframework.demo.mvp.presenter;


import com.xrjframework.demo.mvp.activity.IInfoView;
import com.xrjframework.demo.mvp.bean.InfoBean;
import com.xrjframework.demo.mvp.model.IInfoModel;
import com.xrjframework.demo.mvp.model.InfoModelImpl;


public class Presenter {
    private IInfoModel infoModel;
    private IInfoView infoView;

    public Presenter(IInfoView infoView) {
        this.infoView = infoView;

        infoModel = new InfoModelImpl();
    }
    public void saveInfo(InfoBean bean) {
        infoModel.setInfo(bean);
    }
    public void getInfo() {
        infoView.setInfo(infoModel.getInfo());
    }
}
