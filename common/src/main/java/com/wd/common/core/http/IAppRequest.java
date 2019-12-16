package com.wd.common.core.http;

import com.wd.common.bean.Banner;
import com.wd.common.bean.CategroyBean;
import com.wd.common.bean.DepartmentBean;
import com.wd.common.bean.DrugsCateBean;
import com.wd.common.bean.DrugsKonwBean;
import com.wd.common.bean.DrugsledgeBean;
import com.wd.common.bean.FindDoctorListBean;
import com.wd.common.bean.FormaBean;
import com.wd.common.bean.HomePageSearch;
import com.wd.common.bean.InformaBean;
import com.wd.common.bean.KnowledgeBean;
import com.wd.common.bean.PlateBean;
import com.wd.common.bean.PopularSearch;
import com.wd.common.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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
    //banner  http://172.17.8.100/health/share/v1/bannersShow
    @GET("health/share/v1/bannersShow")
    Observable<Result<List<Banner>>> bannersShow();

    // 查询健康资讯板块       http://172.17.8.100/health/share/information/v1/findInformationPlateList
    @GET("health/share/information/v1/findInformationPlateList")
    Observable<Result<List<PlateBean>>> findInformationPlateList();

    //根据资讯板块查询资讯列表  http://172.17.8.100/health/share/information/v1/findInformationList
    @GET("health/share/information/v1/findInformationList")
    Observable<Result<List<FormaBean>>> findInformationList(@Query("plateId") int plateId,
                                                            @Query("page") int page,
                                                            @Query("count") int count);

    //查询健康讲堂类目
    @GET("health/user/video/v1/findVideoCategoryList")
    Observable<Result<List<VideoGroup>>> findVideoCategoryList();


    //根据视频类目查询视频列表
    @GET("health/user/video/v1/findVideoVoList")
    Observable<Result<List<VideoVo>>> findVideoVoList(@Header("userId") int userId,
                                                      @Header("sessionId") String sessionId,
                                                      @Query("categoryId") int categoryId,
                                                      @Query("page") int page,
                                                      @Query("count") int count
    );


    //健康课堂视频购买
    @POST("health/user/video/verify/v1/videoBuy")
    @FormUrlEncoded
    Observable<Result> videoBuy(@Header("userId") int userId,
                                @Header("sessionId") String sessionId,
                                @Field("videoId") int videoId,
                                @Field("price") int price
    );

    //发表视频评论（弹幕）
    @POST("health/user/video/verify/v1/addVideoComment")
    @FormUrlEncoded
    Observable<Result> addVideoComment(@Header("userId") int userId,
                                       @Header("sessionId") String sessionId,
                                       @Field("videoId") int videoId,
                                       @Field("price") String price
    );

    //查询视频评论列表
    @GET("health/user/video/v1/findVideoCommentList")
    Observable<Result<List<VideoComment>>> findVideoCommentList(@Query("videoId") int videoId);


    //咨询详情  http://172.17.8.100/health/share/information/v1/findInformation
    @GET("health/share/information/v1/findInformation")
    Observable<Result<InformaBean>> findInformation(@Header("userId") String userId,
                                                    @Header("sessionId") String sessionId,
                                                    @Query("infoId") int infoId);

    //  查询科室列表  http://172.17.8.100/health/share/knowledgeBase/v1/findDepartment
    @GET("health/share/knowledgeBase/v1/findDepartment")
    Observable<Result<List<DepartmentBean>>> findDepartment();

    //根据科室查询对应病症 http://172.17.8.100/health/share/knowledgeBase/v1/findDiseaseCategory
    @GET("health/share/knowledgeBase/v1/findDiseaseCategory")
    Observable<Result<List<CategroyBean>>> findDiseaseCategory(@Query("departmentId") int departmentId);

    //查询常见病症详情  http://172.17.8.100/health/share/knowledgeBase/v1/findDiseaseKnowledge
    @GET("health/share/knowledgeBase/v1/findDiseaseKnowledge")
    Observable<Result<KnowledgeBean>> findDiseaseKnowledge(@Query("id") int id);

    //药品科目分类列表查询  http://172.17.8.100/health/share/knowledgeBase/v1/findDrugsCategoryList
    @GET("health/share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<Result<List<DrugsCateBean>>> findDepfindDrugsCategoryListartment();

    //根据药品类目查询常见药品  http://172.17.8.100/health/share/knowledgeBase/v1/findDrugsKnowledgeList
    @GET("health/share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<Result<List<DrugsKonwBean>>> findDrugsKnowledgeList(@Query("drugsCategoryId") int drugsCategoryId,
                                                                   @Query("page") int page,
                                                                   @Query("count") int count);

    //查询常见药品详情  http://172.17.8.100/health/share/knowledgeBase/v1/findDrugsKnowledge
    @GET("health/share/knowledgeBase/v1/findDrugsKnowledge")
    Observable<Result<DrugsledgeBean>> findDrugsKnowledge(@Query("id") int id);

    //热门搜索 http://172.17.8.100/health/share/v1/popularSearch
    @GET("health/share/v1/popularSearch")
    Observable<Result<List<PopularSearch>>> popularSearch();

    //首页搜索 http://172.17.8.100/health/share/v1/homePageSearch
    @GET("health/share/v1/homePageSearch")
    Observable<Result<HomePageSearch>> homePageSearch(@Query("keyWord") String keyWord);

    //收藏 ：http://172.17.8.100/health/user/verify/v1/addInfoCollection
    @POST("health/user/verify/v1/addInfoCollection")
    @FormUrlEncoded
    Observable<Result> addInfoCollection(@Header("userId") String userId,
                                         @Header("sessionId") String sessionId,
                                         @Field("infoId") int infoId);

    //取消收藏 http://172.17.8.100/health/user/verify/v1/cancelInfoCollection
    @POST("health/user/verify/v1/cancelInfoCollection")
    @FormUrlEncoded
    Observable<Result> cancelInfoCollection(@Header("userId") String userId,
                                            @Header("sessionId") String sessionId,
                                            @Field("infoId") int infoId);

    //健康课堂视频收藏
    @POST("health/user/video/verify/v1/addUserVideoCollection")
    @FormUrlEncoded
    Observable<Result> addUserVideoCollection(@Header("userId") int userId,
                                              @Header("sessionId") String sessionId,
                                              @Field("videoId") int videoId);

    //查询问诊医生列表 http://172.17.8.100/health/user/inquiry/v1/findDoctorList
    @GET("health/user/inquiry/v1/findDoctorList")
    Observable<Result<List<FindDoctorListBean>>> findDoctorList(@Query("deptId") int deptId,
                                                                @Query("condition") int condition,
                                                                @Query("sortBy") int sortBy,
                                                                @Query("page") int page,
                                                                @Query("count") int count);

    //查询医生明细信息 http://172.17.8.100/health/user/inquiry/v1/findDoctorInfo
    @GET("health/user/inquiry/v1/findDoctorInfo")
    Observable<Result<InformaBean>> findDoctorInfo(@Header("userId") String userId,
                                                   @Header("sessionId") String sessionId,
                                                   @Query("doctorId") int doctorId);

    //咨询医生 http://172.17.8.100/health/user/inquiry/verify/v1/consultDoctor
    @PUT("health/user/inquiry/verify/v1/consultDoctor")
    Observable<Result> consultDoctor(@Header("userId") String userId,
                                                  @Header("sessionId") String sessionId,
                                                  @Field("doctorId") int doctorId);

    //关注医生 http://172.17.8.100/health/user/inquiry/verify/v1/followDoctor
    @POST("health/user/inquiry/verify/v1/followDoctor")
    @FormUrlEncoded
    Observable<Result> followDoctor(@Header("userId") String userId,
                                    @Header("sessionId") String sessionId,
                                    @Field("doctorId") int doctorId);

    //取消关注医生 http://172.17.8.100/health/user/inquiry/verify/v1/cancelFollow
    @DELETE("health/user/inquiry/verify/v1/cancelFollow")
    @FormUrlEncoded
    Observable<Result> cancelFollow(@Header("userId") String userId,
                                    @Header("sessionId") String sessionId,
                                    @Field("doctorId") int doctorId);

    //结束问诊 http://172.17.8.100/health/user/inquiry/verify/v1/endInquiry
    @PUT("health/user/inquiry/verify/v1/endInquiry")
    Observable<Result<InformaBean>> endInquiry(@Header("userId") String userId,
                                               @Header("sessionId") String sessionId,
                                               @Field("doctorId") int doctorId);
}
