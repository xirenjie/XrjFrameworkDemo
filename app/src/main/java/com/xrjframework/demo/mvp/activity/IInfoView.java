package com.xrjframework.demo.mvp.activity;


import com.xrjframework.demo.mvp.bean.InfoBean;

/**
 * Author       : yanbo
 * Date         : 2015-05-11
 * Time         : 17:30
 * Description  :
 */
public interface IInfoView {
    void setInfo(InfoBean info);
    InfoBean getInfo();
}
