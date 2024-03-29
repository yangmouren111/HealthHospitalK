package com.wd.health_main.fragment.fragment_common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.wd.common.bean.DrugsCateBean;
import com.wd.common.bean.DrugsKonwBean;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDFragment;
import com.wd.common.core.exception.ApiException;
import com.wd.health_main.R;
import com.wd.health_main.R2;
import com.wd.health_main.activity.DiseaseKnowledgeActivity;
import com.wd.health_main.activity.DrugsKnowledgeActivity;
import com.wd.health_main.adapter.Disease_Child_Adaper;
import com.wd.health_main.adapter.Drugs_Child_Adaper;
import com.wd.health_main.adapter.Drugs_Parent_Adaper;
import com.wd.health_main.presenter.DrugsCatePresenter;
import com.wd.health_main.presenter.DrugsKonwPresenter;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description: 常见药品
 */
public class DrugsFragment extends WDFragment {
    @BindView(R2.id.frag_drugs_recycl)
    RecyclerView fragDrugsRecycl;
    @BindView(R2.id.frag_drugs_view)
    RecyclerView fragDrugsView;
    private DrugsCatePresenter drugsCatePresenter;
    private Drugs_Parent_Adaper drugs_parent_adaper;
    private DrugsKonwPresenter drugsKonwPresenter;
    private Drugs_Child_Adaper drugs_child_adaper;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_drugs;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        fragDrugsRecycl.setLayoutManager(linearLayoutManager);
        drugsCatePresenter = new DrugsCatePresenter(new drugsCate());
        drugsCatePresenter.reqeust();
        drugs_parent_adaper = new Drugs_Parent_Adaper();
        fragDrugsRecycl.setAdapter(drugs_parent_adaper);
        drugsKonwPresenter = new DrugsKonwPresenter(new drugsKonw());
        drugsKonwPresenter.reqeust(1, 1, 5);
        drugs_parent_adaper.setOnItemClickListener(new Drugs_Parent_Adaper.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                drugsKonwPresenter.reqeust(position, 1, 5);
                drugs_parent_adaper.setmPosition(position);
                drugs_parent_adaper.notifyDataSetChanged();
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        fragDrugsView.setLayoutManager(gridLayoutManager);
        drugs_child_adaper = new Drugs_Child_Adaper();
        fragDrugsView.setAdapter(drugs_child_adaper);
        drugs_child_adaper.setOnItemClickListener(new Drugs_Child_Adaper.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), DrugsKnowledgeActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
                SharedPreferences sp = getContext().getSharedPreferences("Drugs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putInt("Drugsid", position);
                edit.commit();
            }

            @Override
            public void onClick(String name) {
                SharedPreferences sp = getContext().getSharedPreferences("Drugs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("Drugsname", name);
                edit.commit();
            }
        });
    }

    private class drugsCate implements DataCall<List<DrugsCateBean>> {
        @Override
        public void success(List<DrugsCateBean> data, Object... args) {
            drugs_parent_adaper.addAll(data);
            drugs_parent_adaper.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class drugsKonw implements DataCall<List<DrugsKonwBean>> {
        @Override
        public void success(List<DrugsKonwBean> data, Object... args) {
            drugs_child_adaper.clear();
            drugs_child_adaper.addAll(data);
            drugs_child_adaper.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
