package com.xrjframework.demo.mvp.model;

import com.xrjframework.demo.mvp.bean.InfoBean;


public class InfoModelImpl implements IInfoModel {
    private InfoBean infoBean = new InfoBean();

    @Override
    public InfoBean getInfo() {
        return infoBean;
    }

    @Override
    public void setInfo(InfoBean info) {
        infoBean = info;
    }
}
