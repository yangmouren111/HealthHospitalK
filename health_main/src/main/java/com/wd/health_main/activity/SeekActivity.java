package com.wd.health_main.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.core.WDActivity;
import com.wd.health_main.R;
import com.wd.health_main.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeekActivity extends WDActivity {


    @BindView(R2.id.search_back)
    SimpleDraweeView searchBack;
    @BindView(R2.id.kylin_view)
    EditText kylinView;
    @BindView(R2.id.search_text)
    TextView searchText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_seek;
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

    @OnClick(R2.id.search_text)
    public void onViewClicked() {
    }
}
