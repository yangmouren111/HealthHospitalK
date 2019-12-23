package com.wd.health_main.fragment;

import android.content.Intent;
import android.widget.ImageView;

import com.wd.common.bean.DuiyingBean;
import com.wd.common.bean.KeshiBean;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDFragment;
import com.wd.common.core.exception.ApiException;
import com.wd.health_main.R;
import com.wd.health_main.R2;
import com.wd.health_main.activity.SeekActivity;
import com.wd.health_main.adapter.DepartmentsAdapter;
import com.wd.health_main.adapter.SocietyAdapter;
import com.wd.health_main.presenter.Departmentspresenter;
import com.wd.health_main.presenter.Duiyingpresenter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Time:  2019-12-04
 * Author:  杨世博
 * Description:
 */
public class CircleFragment extends WDFragment {


    @BindView(R2.id.recy_departments)
    RecyclerView recyDepartments;
    @BindView(R2.id.image_search)
    ImageView imageSearch;
    @BindView(R2.id.recy_departments1)
    RecyclerView recyDepartments1;
    private Departmentspresenter departmentspresenter;
    private DepartmentsAdapter departmentsAdapter;
    private SocietyAdapter societyAdapter;
    private Duiyingpresenter duiyingpresenter;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_circle;
    }

    @Override
    protected void initView() {
        departmentspresenter = new Departmentspresenter(new Keshi());
        departmentspresenter.reqeust();
        recyDepartments.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        departmentsAdapter = new DepartmentsAdapter();
        recyDepartments.setAdapter(departmentsAdapter);
        departmentsAdapter.setListener(new DepartmentsAdapter.CheckedListener() {
            @Override
            public void cheked(int position) {
                duiyingpresenter.reqeust(position,2,5);
            }
        });

        duiyingpresenter = new Duiyingpresenter(new Duiying());

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyDepartments1.setLayoutManager(linearLayoutManager1);
        societyAdapter = new SocietyAdapter();
        recyDepartments1.setAdapter(societyAdapter);




    }

    @OnClick(R2.id.image_search)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), SeekActivity.class);
        startActivity(intent);


    }

    private class Keshi implements DataCall<List<KeshiBean>> {
        @Override
        public void success(List<KeshiBean> data, Object... args) {
        departmentsAdapter.addAll(data);
        departmentsAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    public class Duiying implements DataCall<List<DuiyingBean>> {

        @Override
        public void success(List<DuiyingBean> data, Object... args) {
            societyAdapter.clear();
            societyAdapter.addAll(data);
            societyAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
