package com.wd.health_main.presenter;

import com.wd.common.core.DataCall;
import com.wd.common.core.WDPresenter;
import com.wd.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @describe(描述)：com.wd.health_main.presenter
 * @data（日期）: 2019/12/17
 * @time（时间）: 15:57
 * @author（作者）: 闫小康
 * @function(功能):
 **/
public class Duiyingpresenter extends WDPresenter<IAppRequest> {
    public Duiyingpresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findduiying((int)args[0],(int) args[1],(int) args[2]);
    }
}
