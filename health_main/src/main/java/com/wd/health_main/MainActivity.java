package com.wd.health_main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wd.common.core.WDActivity;
import com.wd.health_main.fragment.CircleFragment;
import com.wd.health_main.fragment.ShowFragment;
import com.wd.health_main.fragment.VideoFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends WDActivity {


    @BindView(R2.id.main_pager)
    ViewPager mainPager;
    @BindView(R2.id.main_btn_home)
    RadioButton mainBtnHome;
    @BindView(R2.id.main_btn_cicle)
    RadioButton mainBtnCicle;
    @BindView(R2.id.main_btn_video)
    RadioButton mainBtnVideo;
    @BindView(R2.id.main_group)
    RadioGroup mainGroup;
    private ArrayList<Fragment> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        list = new ArrayList<>();
        list.add(new ShowFragment());
        list.add(new CircleFragment());
        list.add(new VideoFragment());


        mainPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        mainPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mainGroup.check(mainGroup.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mainGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("InvalidR2Usage")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R2.id.main_btn_home:
                        mainPager.setCurrentItem(0);
                        break;
                    case R2.id.main_btn_cicle:
                        mainPager.setCurrentItem(1);
                        break;
                    case R2.id.main_btn_video:
                        mainPager.setCurrentItem(2);
                        break;
                }
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


}
