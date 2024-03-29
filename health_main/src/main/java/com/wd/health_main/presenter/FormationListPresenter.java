package com.wd.health_main.presenter;

import com.wd.common.core.DataCall;
import com.wd.common.core.WDPresenter;
import com.wd.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description: 根据资讯板块查询资讯列表
 */
public class FormationListPresenter extends WDPresenter<IAppRequest> {
    public FormationListPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findInformationList((int) args[0], (int) args[1], (int) args[2]);
    }
}
