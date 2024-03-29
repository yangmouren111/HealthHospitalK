package com.wd.health_main.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.wd.common.bean.Banner;
import com.wd.common.bean.DepartmentBean;
import com.wd.common.bean.FormaBean;
import com.wd.common.bean.PlateBean;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDFragment;
import com.wd.common.core.exception.ApiException;
import com.wd.health_main.R;
import com.wd.health_main.R2;
import com.wd.health_main.activity.BannerActivity;
import com.wd.health_main.activity.Consultation_DetailsActivity;
import com.wd.health_main.activity.DiseaseActivity;
import com.wd.health_main.activity.MoreActivity;
import com.wd.health_main.activity.SearchActivity;
import com.wd.health_main.adapter.ConsultAdapter;
import com.wd.health_main.adapter.ShowOutAdapter;
import com.wd.health_main.adapter.Show_InquiryAdaper;
import com.wd.health_main.presenter.BannerPresenter;
import com.wd.health_main.presenter.DepartmentPresenter;
import com.wd.health_main.presenter.FormationListPresenter;
import com.wd.health_main.presenter.PlateListPresenter;
import com.wd.health_main.seach.KylinSearchView_Out;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import health.wd.com.health_query.activity.QueryActivity;

/**
 * Time:  2019-12-04
 * Author:  杨世博
 * Description: 展示首页
 */

public class ShowFragment extends WDFragment {

    @BindView(R2.id.show_banner)
    XBanner showBanner;
    @BindView(R2.id.show_disease)
    ImageView showDisease;
    @BindView(R2.id.show_drugs)
    ImageView showDrugs;
    @BindView(R2.id.show_image)
    ImageView showImage;
    @BindView(R2.id.show_recyvle_view)
    RecyclerView showRecyvleView;
    @BindView(R2.id.show_out_view)
    RecyclerView showOutView;
    @BindView(R2.id.show_inner_view)
    RecyclerView showInnerView;
    @BindView(R2.id.kylin_out)
    KylinSearchView_Out kylinOut;
    @BindView(R2.id.show_more)
    TextView showMore;

    private FormationListPresenter formationListPresenter;
    private PlateListPresenter plateListPresenter;
    private BannerPresenter bannerPresenter;
    private DepartmentPresenter departmentPresenter;
    private Show_InquiryAdaper show_inquiryAdaper;
    private ShowOutAdapter showOutAdapter;
    private ConsultAdapter consultAdapter;
    private int pos;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_show;
    }

    @Override
    protected void initView() {
        bannerPresenter = new BannerPresenter(new banner());
        bannerPresenter.reqeust();
        //健康养生

        formationListPresenter = new FormationListPresenter(new format());
        formationListPresenter.reqeust(1, 1, 5);
        departmentPresenter = new DepartmentPresenter(new departt());
        departmentPresenter.reqeust();
        show_inquiryAdaper = new Show_InquiryAdaper();
        showRecyvleView.setAdapter(show_inquiryAdaper);
        show_inquiryAdaper.setOnItemClickListener(new Show_InquiryAdaper.OnItemClickListener() {
            @Override
            public void onClick(int position) {
              Intent intent = new Intent(getContext(),QueryActivity.class);
              intent.putExtra("showZixunId",position);
              startActivity(intent);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        showRecyvleView.setLayoutManager(gridLayoutManager);
        plateListPresenter = new PlateListPresenter(new plate());
        plateListPresenter.reqeust();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        showOutView.setLayoutManager(linearLayoutManager);
        showOutAdapter = new ShowOutAdapter();
        showOutView.setAdapter(showOutAdapter);
        showOutAdapter.setOnItemClickListener(new ShowOutAdapter.OnItemClickListener() {

            @Override
            public void onClick(int position) {
                pos = position;
                formationListPresenter.reqeust(pos, 1, 5);
                showOutAdapter.setmPosition(pos);
                showOutAdapter.notifyDataSetChanged();
            }
        });
        SharedPreferences sp = getContext().getSharedPreferences("Disease", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("showid", pos);
        edit.commit();

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        showInnerView.setLayoutManager(linearLayoutManager1);
        consultAdapter = new ConsultAdapter();
        showInnerView.setAdapter(consultAdapter);
        consultAdapter.setOnItemClickListener(new ConsultAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), Consultation_DetailsActivity.class);
                intent.putExtra("infoId", position);
                startActivity(intent);
            }
        });
    }

    @OnClick({R2.id.show_banner, R2.id.show_disease, R2.id.show_drugs, R2.id.show_image, R2.id.show_more})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.show_banner) {
            intent(BannerActivity.class);
        } else if (i == R.id.show_disease) {
            intent(DiseaseActivity.class);
        } else if (i == R.id.show_drugs) {
            intent(DiseaseActivity.class);
        } else if (i == R.id.show_image) {
        } else if (i == R.id.show_more) {
            intent(MoreActivity.class);
        }
    }

    @OnClick(R2.id.kylin_out)
    public void onViewClicked() {
        intent(SearchActivity.class);
    }

    private class banner implements DataCall<List<Banner>> {
        @Override
        public void success(final List<Banner> data, Object... args) {
            showBanner.setData(data, null);
            showBanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {
                    Glide.with(getActivity()).load(data.get(position).imageUrl).into((ImageView) view);
                    banner.setmAutoPalyTime(3000);
                    banner.startAutoPlay();
                    String jumpUrl = data.get(position).jumpUrl;
                    String title = data.get(position).title;
                    SharedPreferences sp = getContext().getSharedPreferences("Disease", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("url", jumpUrl);
                    edit.commit();
                }

            });
            showBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, int position) {
                    Intent intent = new Intent(getContext(),BannerActivity.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class plate implements DataCall<List<PlateBean>> {

        @Override
        public void success(List<PlateBean> data, Object... args) {
            showOutAdapter.addAll(data);
            show_inquiryAdaper.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class format implements DataCall<List<FormaBean>> {
        @Override
        public void success(List<FormaBean> data, Object... args) {
            consultAdapter.clear();
            consultAdapter.addAll(data);
            consultAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class departt implements DataCall<List<DepartmentBean>> {
        @Override
        public void success(List<DepartmentBean> data, Object... args) {
            show_inquiryAdaper.addAll(data);
            show_inquiryAdaper.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
