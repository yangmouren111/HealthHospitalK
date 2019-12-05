package com.wd.health_main.fragment.fragment_common;

import android.content.Intent;

import com.wd.common.bean.CategroyBean;
import com.wd.common.bean.DepartmentBean;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDFragment;
import com.wd.common.core.exception.ApiException;
import com.wd.health_main.R;
import com.wd.health_main.R2;
import com.wd.health_main.activity.DiseaseKnowledgeActivity;
import com.wd.health_main.adapter.Disease_Child_Adaper;
import com.wd.health_main.adapter.Disease_Parent_Adaper;
import com.wd.health_main.presenter.CategroyPresenter;
import com.wd.health_main.presenter.DepartmentPresenter;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description:  常见病症
 */
public class DiseaseFragment extends WDFragment {
    @BindView(R2.id.frag_disease_recycl)
    RecyclerView fragDiseaseRecycl;
    @BindView(R2.id.frag_disease_view)
    RecyclerView fragDiseaseView;
    private DepartmentPresenter departmentPresenter;
    private Disease_Parent_Adaper disease_parent_adaper;
    private CategroyPresenter categroyPresenter;
    private Disease_Child_Adaper disease_child_adaper;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_disease;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        fragDiseaseRecycl.setLayoutManager(linearLayoutManager);
        disease_parent_adaper = new Disease_Parent_Adaper();
        fragDiseaseRecycl.setAdapter(disease_parent_adaper);
        departmentPresenter = new DepartmentPresenter(new depart());
        departmentPresenter.reqeust();
        categroyPresenter = new CategroyPresenter(new categroy());
        categroyPresenter.reqeust(7);
        disease_parent_adaper.setOnItemClickListener(new Disease_Parent_Adaper.OnItemClickListener() {
            @Override
            public void onClick(int position) {
              categroyPresenter.reqeust(position);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        fragDiseaseView.setLayoutManager(gridLayoutManager);
        disease_child_adaper = new Disease_Child_Adaper();
        fragDiseaseView.setAdapter(disease_child_adaper);
        disease_child_adaper.setOnItemClickListener(new Disease_Child_Adaper.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(),DiseaseKnowledgeActivity.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });
    }

    private class depart implements DataCall<List<DepartmentBean>> {
        @Override
        public void success(List<DepartmentBean> data, Object... args) {
            disease_parent_adaper.addAll(data);
            disease_parent_adaper.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class categroy implements DataCall<List<CategroyBean>> {
        @Override
        public void success(List<CategroyBean> data, Object... args) {
            disease_child_adaper.clear();
            disease_child_adaper.addAll(data);
            disease_child_adaper.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
