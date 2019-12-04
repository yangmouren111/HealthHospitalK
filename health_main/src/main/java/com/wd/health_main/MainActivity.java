package com.wd.health_main;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wd.common.core.WDActivity;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends WDActivity {


    @BindView(R2.id.main_pager)
    ViewPager mainPager;
    @BindView(R2.id.main_btn_home)
    RadioButton mainBtnHome;
    @BindView(R2.id.main_btn_cicle)
    RadioButton mainBtnCicle;
    @BindView(R2.id.main_btn_video)
    RadioButton mainBtnVideo;
    @BindView(R2.id.main_group)
    RadioGroup mainGroup;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.main_btn_home, R2.id.main_btn_cicle, R2.id.main_btn_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.main_btn_home:
                break;
            case R2.id.main_btn_cicle:
                break;
            case R2.id.main_btn_video:
                break;
        }
    }

}
