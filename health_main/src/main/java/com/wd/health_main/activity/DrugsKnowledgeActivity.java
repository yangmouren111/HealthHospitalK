package com.wd.health_main.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.wd.common.bean.DrugsledgeBean;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDActivity;
import com.wd.common.core.exception.ApiException;
import com.wd.health_main.R;
import com.wd.health_main.R2;
import com.wd.health_main.adapter.DiseaseKnowledgeAdapter;
import com.wd.health_main.adapter.DrugsKnowledgeAdapter;
import com.wd.health_main.presenter.DrugsledgePresenter;
import com.wd.health_main.presenter.KnowledgePresenter;

import java.util.Arrays;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DrugsKnowledgeActivity extends WDActivity {


    @BindView(R2.id.drug_kon_name)
    TextView drugKonName;
    @BindView(R2.id.drugs_knowledge_view)
    RecyclerView drugsKnowledgeView;
    private DrugsKnowledgeAdapter drugsKnowledgeAdapter;
    private int drugsid;
    private String drugsname;
    private DrugsledgePresenter drugsledgePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drugs_knowledge;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DrugsKnowledgeActivity.this);
        drugsKnowledgeView.setLayoutManager(linearLayoutManager);
        SharedPreferences sp = getSharedPreferences("Drugs", Context.MODE_PRIVATE);
        drugsid = sp.getInt("Drugsid", 0);
        drugsname = sp.getString("Drugsname", "");
        drugKonName.setText(drugsname);
        drugsledgePresenter = new DrugsledgePresenter(new Drugsle());
        drugsledgePresenter.reqeust(drugsid);
        drugsKnowledgeAdapter = new DrugsKnowledgeAdapter();
        drugsKnowledgeView.setAdapter(drugsKnowledgeAdapter);
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

    private class Drugsle implements DataCall<DrugsledgeBean> {
        @Override
        public void success(DrugsledgeBean data, Object... args) {
            drugsKnowledgeAdapter.addAll(Arrays.asList(data));
            drugsKnowledgeAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
