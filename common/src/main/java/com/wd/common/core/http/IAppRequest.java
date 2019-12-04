package com.wd.common.core.http;

import com.wd.common.bean.Banner;
import com.wd.common.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IAppRequest {
//bannerhttp://172.17.8.100/health/share/v1/bannersShow
    @GET("health/share/v1/bannersShow")
    Observable<Result<List<Banner>>> bannersShow();

}
