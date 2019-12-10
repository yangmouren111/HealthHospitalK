package com.wd.common.core.http;

import com.wd.common.bean.BDResult;
import com.wd.common.bean.Result;
import com.wd.common.bean.VideoComment;
import com.wd.common.bean.VideoGroup;
import com.wd.common.bean.VideoVo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IAppRequest {

    //查询健康讲堂类目
    @GET("user/video/v1/findVideoCategoryList")
    Observable<Result<List<VideoGroup>>> findVideoCategoryList();


    //根据视频类目查询视频列表
    @GET("user/video/v1/findVideoVoList")
    Observable<Result<List<VideoVo>>> findVideoVoList(@Header("userId") int userId,
                                                      @Header("sessionId") String sessionId,
                                                      @Query("categoryId") int categoryId,
                                                      @Query("page") int page,
                                                      @Query("count") int count
    );


    //健康课堂视频购买
    @POST("user/video/verify/v1/videoBuy")
    @FormUrlEncoded
    Observable<Result> videoBuy(@Header("userId") int userId,
                                                      @Header("sessionId") String sessionId,
                                                      @Field("videoId") int videoId,
                                                      @Field("price") int price
    );

    //发表视频评论（弹幕）
    @POST("user/video/verify/v1/addVideoComment")
    @FormUrlEncoded
    Observable<Result> addVideoComment(@Header("userId") int userId,
                                                      @Header("sessionId") String sessionId,
                                                      @Field("videoId") int videoId,
                                                      @Field("price") String price
    );
    //查询视频评论列表
    @GET("user/video/v1/findVideoCommentList")
    Observable<Result<List<VideoComment>>> findVideoCommentList(@Query("videoId") int videoId);


}
