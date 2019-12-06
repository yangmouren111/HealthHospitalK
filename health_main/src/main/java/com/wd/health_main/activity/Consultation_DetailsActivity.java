package com.wd.health_main.activity;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.bean.InformaBean;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDActivity;
import com.wd.common.core.exception.ApiException;
import com.wd.health_main.R;
import com.wd.health_main.R2;
import com.wd.health_main.adapter.Consultation_DetailsAdapter;
import com.wd.health_main.core.HtmlUtils;
import com.wd.health_main.presenter.FindInformationPresenter;

import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Consultation_DetailsActivity extends WDActivity {


    @BindView(R2.id.consultation_back)
    SimpleDraweeView consultationBack;
    @BindView(R2.id.consultation_view)
    RecyclerView consultationView;
    private Consultation_DetailsAdapter consultation_detailsAdapter;
    private FindInformationPresenter findInformationPresenter;
    private int infoId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_consultation__details;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        infoId = intent.getIntExtra("infoId", 0);
        findInformationPresenter = new FindInformationPresenter(new FindInform());
        findInformationPresenter.reqeust(" ", " ", infoId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Consultation_DetailsActivity.this);
        consultationView.setLayoutManager(linearLayoutManager);
        consultation_detailsAdapter = new Consultation_DetailsAdapter();
        consultationView.setAdapter(consultation_detailsAdapter);
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

    @OnClick(R2.id.consultation_back)
    public void onViewClicked() {
        finish();
    }

    private class FindInform implements DataCall<InformaBean> {
        @Override
        public void success(InformaBean data, Object... args) {
            consultation_detailsAdapter.addAll(Arrays.asList(data));
            consultation_detailsAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
