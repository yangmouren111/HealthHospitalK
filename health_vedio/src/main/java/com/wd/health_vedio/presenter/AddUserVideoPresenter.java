package com.wd.health_vedio.presenter;

import com.wd.common.core.DataCall;
import com.wd.common.core.WDPresenter;
import com.wd.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @describe(描述)：com.wd.health_vedio.presenter
 * @data（日期）: 09:2019/12/5
 * @time（时间）: 9:59
 * @author（作者）: 盖磊
 **/
public class AddUserVideoPresenter extends WDPresenter<IAppRequest> {

    public AddUserVideoPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.addUserVideoCollection((int)args[0],(String) args[1],(int)args[2]);
    }
}
