package com.xrjframework.demo.nice.view;

import com.xrj.framework.nice.view.IBaseView;
import com.xrjframework.demo.nice.bean.GankItemData;

import java.util.List;


/**
 * Author: Othershe
 * Time: 2016/8/12 14:31
 */
public interface GankItemView extends IBaseView {
    void onSuccess(List<GankItemData> data);
}
