package com.wd.common.core.http;

import com.wd.common.bean.Banner;
import com.wd.common.bean.CategroyBean;
import com.wd.common.bean.DepartmentBean;
import com.wd.common.bean.DrugsCateBean;
import com.wd.common.bean.DrugsKonwBean;
import com.wd.common.bean.DrugsledgeBean;
import com.wd.common.bean.FormaBean;
import com.wd.common.bean.InformaBean;
import com.wd.common.bean.KnowledgeBean;
import com.wd.common.bean.PlateBean;
import com.wd.common.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    //咨询详情  http://172.17.8.100/health/share/information/v1/findInformation
    @GET("health/share/information/v1/findInformation")
    Observable<Result<List<InformaBean>>> findInformation(@Header("userId") String userId,
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
}
