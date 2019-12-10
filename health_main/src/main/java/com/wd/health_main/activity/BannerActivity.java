package com.wd.health_main.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.common.core.WDActivity;
import com.wd.health_main.R;
import com.wd.health_main.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerActivity extends WDActivity {


    @BindView(R2.id.banner_view)
    ImageView bannerView;
    @BindView(R2.id.banner_name)
    TextView bannerName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_banner;
    }

    @Override
    protected void initView() {
        SharedPreferences sp = getSharedPreferences("Disease", Context.MODE_PRIVATE);
        String url = sp.getString("url", "");
        Glide.with(BannerActivity.this).load(url).into(bannerView);
        String urlname = sp.getString("urlname", "");
        bannerName.setText(urlname);
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
