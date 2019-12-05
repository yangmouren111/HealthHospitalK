package com.wd.health_main.presenter;

import com.wd.common.core.DataCall;
import com.wd.common.core.WDPresenter;
import com.wd.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description: 查询常见病症详情
 */
public class KnowledgePresenter extends WDPresenter<IAppRequest> {
    public KnowledgePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findDiseaseKnowledge((int) args[0]);
    }
}
