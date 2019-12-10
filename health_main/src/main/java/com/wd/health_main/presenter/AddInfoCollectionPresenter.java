package com.wd.health_main.presenter;

import com.wd.common.core.DataCall;
import com.wd.common.core.WDPresenter;
import com.wd.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * Time:  2019-12-10
 * Author:  杨世博
 * Description:
 */
public class AddInfoCollectionPresenter extends WDPresenter<IAppRequest> {
    public AddInfoCollectionPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.addInfoCollection((String) args[0], (String) args[1], (int) args[2]);
    }
}
