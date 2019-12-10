package com.wd.health_vedio.presenter;

import com.wd.common.bean.Result;
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
public class FindVideoCategoryPresenter extends WDPresenter<IAppRequest> {
    public FindVideoCategoryPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findVideoCategoryList();
    }
}
