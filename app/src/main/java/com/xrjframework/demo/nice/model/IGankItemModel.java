package com.xrjframework.demo.nice.model;


import com.xrj.framework.nice.data.HttpResult;
import com.xrjframework.demo.nice.bean.GankItemData;

import java.util.List;

import rx.Observable;

/**
 * Author: Othershe
 * Time: 2016/8/12 14:30
 */
public interface IGankItemModel {
    Observable<HttpResult<List<GankItemData>>> getGankItemData(String suburl);
}
