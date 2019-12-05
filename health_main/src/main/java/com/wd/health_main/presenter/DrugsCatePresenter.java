package com.wd.health_main.presenter;

import com.wd.common.core.DataCall;
import com.wd.common.core.WDPresenter;
import com.wd.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description: 药品科目分类列表查询
 */
public class DrugsCatePresenter extends WDPresenter<IAppRequest>{
    public DrugsCatePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.findDepfindDrugsCategoryListartment();
    }
}
