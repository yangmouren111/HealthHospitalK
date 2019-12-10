package com.wd.health_main.presenter;

import com.wd.common.core.DataCall;
import com.wd.common.core.WDPresenter;
import com.wd.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * Time:  2019-12-09
 * Author:  杨世博
 * Description:
 */
public class PopularSearchPresenter extends WDPresenter<IAppRequest> {
    public PopularSearchPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.popularSearch();
    }
}
