package com.wd.health_main.presenter;

import com.wd.common.core.DataCall;
import com.wd.common.core.WDPresenter;
import com.wd.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description:  根据科室查询对应病症
 */
public class CategroyPresenter extends WDPresenter<IAppRequest> {
    public CategroyPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findDiseaseCategory((int) args[0]);
    }
}
