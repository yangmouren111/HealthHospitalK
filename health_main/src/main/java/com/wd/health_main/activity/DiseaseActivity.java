package com.wd.health_main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.common.core.WDActivity;
import com.wd.common.util.Constant;
import com.wd.health_main.R;

/**
 * 常见病症
 */
@Route(path = Constant.ACTIVITY_URL_DISE)
public class DiseaseActivity extends WDActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_disease;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }
}
