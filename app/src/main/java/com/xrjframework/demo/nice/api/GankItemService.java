package com.xrjframework.demo.nice.api;


import com.xrj.framework.nice.data.HttpResult;
import com.xrjframework.demo.Api;
import com.xrjframework.demo.nice.bean.GankItemData;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Author: Othershe
 * Time: 2016/8/12 16:50
 */
public interface GankItemService {
    String BASE_URL = Api.URL_GET_GANK;

    @GET("{suburl}")
    Observable<HttpResult<List<GankItemData>>> getGankItemData(@Path("suburl") String suburl);
}
