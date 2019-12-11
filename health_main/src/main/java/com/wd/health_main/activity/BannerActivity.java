package com.wd.health_main.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;

import com.bumptech.glide.Glide;
import com.wd.common.core.WDActivity;
import com.wd.health_main.R;
import com.wd.health_main.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerActivity extends WDActivity {


    @BindView(R2.id.banner_view)
    WebView bannerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_banner;
    }

    @Override
    protected void initView() {
        SharedPreferences sp = getSharedPreferences("Disease", Context.MODE_PRIVATE);
        String url = sp.getString("url", "");
       bannerView.loadUrl(url);
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
}
