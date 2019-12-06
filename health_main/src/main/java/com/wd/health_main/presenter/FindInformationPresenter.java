package com.wd.health_main.presenter;

import com.wd.common.core.DataCall;
import com.wd.common.core.WDPresenter;
import com.wd.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * Time:  2019-12-06
 * Author:  杨世博
 * Description:
 */
public class FindInformationPresenter extends WDPresenter<IAppRequest> {
    public FindInformationPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findInformation((String) args[0], (String) args[1], (int) args[2]);
    }
}
