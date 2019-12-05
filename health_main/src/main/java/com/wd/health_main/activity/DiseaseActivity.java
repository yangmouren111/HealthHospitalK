package com.wd.health_main.activity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.wd.common.core.WDActivity;
import com.wd.health_main.R;
import com.wd.health_main.R2;
import com.wd.health_main.adapter.FragAdapter;
import com.wd.health_main.fragment.fragment_common.DiseaseFragment;
import com.wd.health_main.fragment.fragment_common.DrugsFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 常见病症
 */
public class DiseaseActivity extends WDActivity {

    @BindView(R2.id.disease_tab)
    TabLayout diseaseTab;
    @BindView(R2.id.disease_view)
    ViewPager diseaseView;
    private FragAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_disease;
    }

    @Override
    protected void initView() {
        List<Fragment> list = new ArrayList<>();

        list.add(new DiseaseFragment());
        list.add(new DrugsFragment());

        adapter = new FragAdapter(getSupportFragmentManager(), list);
        diseaseView.setAdapter(adapter);
        diseaseTab.setupWithViewPager(diseaseView);

        diseaseTab.getTabAt(0).setText("常见病症");
        diseaseTab.getTabAt(1).setText("常用药品");
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
