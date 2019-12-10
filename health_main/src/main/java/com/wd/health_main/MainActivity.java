package com.wd.health_main;

import com.wd.common.core.WDActivity;
import com.wd.health_vedio.view.activity.VideoActivity;

import butterknife.OnClick;
/**
 *@describe(描述)：MainActivity
 *@data（日期）: 2019/12/5
 *@time（时间）: 13:49
 *@author（作者）: 盖磊
 **/
public class MainActivity extends WDActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }


    @OnClick(R2.id.to_intent)
    public void onViewClicked() {
        intent(VideoActivity.class);
    }
}
