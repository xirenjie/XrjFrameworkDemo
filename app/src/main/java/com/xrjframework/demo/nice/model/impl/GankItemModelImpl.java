package com.xrjframework.demo.nice.model.impl;

import com.xrj.framework.nice.data.HttpResult;
import com.xrj.framework.nice.net.NetManager;
import com.xrjframework.demo.nice.api.GankItemService;
import com.xrjframework.demo.nice.bean.GankItemData;
import com.xrjframework.demo.nice.model.IGankItemModel;

import java.util.List;

import rx.Observable;

/**
 * Author: Othershe
 * Time: 2016/8/12 14:30
 */
public class GankItemModelImpl implements IGankItemModel {

    @Override
    public Observable<HttpResult<List<GankItemData>>> getGankItemData(String suburl) {
        GankItemService service = NetManager.getInstance().create(GankItemService.class);
        return service.getGankItemData(suburl);
    }
}
