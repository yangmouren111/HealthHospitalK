package com.wd.common.core.http;

import com.wd.common.bean.Banner;
import com.wd.common.bean.FormaBean;
import com.wd.common.bean.PlateBean;
import com.wd.common.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IAppRequest {
    //banner  http://172.17.8.100/health/share/v1/bannersShow
    @GET("health/share/v1/bannersShow")
    Observable<Result<List<Banner>>> bannersShow();

    // 查询健康资讯板块       http://172.17.8.100/health/share/information/v1/findInformationPlateList
    @GET("health/share/information/v1/findInformationPlateList")
    Observable<Result<List<PlateBean>>> findInformationPlateList();

    //根据资讯板块查询资讯列表  http://172.17.8.100/health/share/information/v1/findInformationList
    @GET("health/share/information/v1/findInformationList")
    Observable<Result<List<FormaBean>>> findInformationList(@Header("plateId") int plateId,
                                                            @Header("page") int page,
                                                            @Header("count") int count);


}
