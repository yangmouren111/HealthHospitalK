package com.wd.health_main.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.wd.common.bean.KnowledgeBean;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDActivity;
import com.wd.common.core.exception.ApiException;
import com.wd.health_main.R;
import com.wd.health_main.R2;
import com.wd.health_main.adapter.DiseaseKnowledgeAdapter;
import com.wd.health_main.presenter.KnowledgePresenter;

import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DiseaseKnowledgeActivity extends WDActivity {


    @BindView(R2.id.knowledge_view)
    RecyclerView knowledgeView;
    @BindView(R2.id.dis_kon_name)
    TextView disKonName;
    private KnowledgePresenter knowledgePresenter;
    private DiseaseKnowledgeAdapter diseaseKnowledgeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_disease_knowledge;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DiseaseKnowledgeActivity.this);
        knowledgeView.setLayoutManager(linearLayoutManager);
        diseaseKnowledgeAdapter = new DiseaseKnowledgeAdapter();
        knowledgeView.setAdapter(diseaseKnowledgeAdapter);
        SharedPreferences sp = getSharedPreferences("Disease", Context.MODE_PRIVATE);
        int diseaseid = sp.getInt("Diseaseid", 0);
        String diseasename = sp.getString("Diseasename", "");
        disKonName.setText(diseasename);
        knowledgePresenter = new KnowledgePresenter(new Knowle());
        knowledgePresenter.reqeust(diseaseid);
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

    private class Knowle implements DataCall<KnowledgeBean> {
        @Override
        public void success(KnowledgeBean data, Object... args) {
            diseaseKnowledgeAdapter.addAll(Arrays.asList(data) );
            diseaseKnowledgeAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
