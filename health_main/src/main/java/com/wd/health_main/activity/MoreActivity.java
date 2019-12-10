package com.wd.health_main.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.common.bean.FormaBean;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDActivity;
import com.wd.common.core.exception.ApiException;
import com.wd.health_main.R;
import com.wd.health_main.R2;
import com.wd.health_main.adapter.ConsultAdapter;
import com.wd.health_main.presenter.FormationListPresenter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreActivity extends WDActivity {

    @BindView(R2.id.more_view)
    XRecyclerView moreView;
    private FormationListPresenter formationListPresenter;
    private ConsultAdapter consultAdapter;
    private int moreid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more;
    }

    @Override
    protected void initView() {
        SharedPreferences sp =getSharedPreferences("Disease",Context.MODE_PRIVATE);
        moreid = sp.getInt("showid",0);


        formationListPresenter = new FormationListPresenter(new format());
        formationListPresenter.reqeust(moreid, 1, 5);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        moreView.setLayoutManager(linearLayoutManager1);
        consultAdapter = new ConsultAdapter();
        moreView.setAdapter(consultAdapter);
        consultAdapter.setOnItemClickListener(new ConsultAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MoreActivity.this, Consultation_DetailsActivity.class);
                intent.putExtra("infoId", position);
                startActivity(intent);
            }
        });
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

    private class format implements DataCall<List<FormaBean>> {
        @Override
        public void success(List<FormaBean> data, Object... args) {
            consultAdapter.addAll(data);
            consultAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
