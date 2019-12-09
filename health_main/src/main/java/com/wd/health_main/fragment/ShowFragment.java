package com.wd.health_main.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
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
import com.wd.health_main.activity.Consultation_DetailsActivity;
import com.wd.health_main.activity.DiseaseActivity;
import com.wd.health_main.activity.DiseaseKnowledgeActivity;
import com.wd.health_main.adapter.ConsultAdapter;
import com.wd.health_main.adapter.ShowOutAdapter;
import com.wd.health_main.adapter.Show_InquiryAdaper;
import com.wd.health_main.presenter.BannerPresenter;
import com.wd.health_main.presenter.DepartmentPresenter;
import com.wd.health_main.presenter.FormationListPresenter;
import com.wd.health_main.presenter.PlateListPresenter;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

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

    private FormationListPresenter formationListPresenter;
    private PlateListPresenter plateListPresenter;
    private BannerPresenter bannerPresenter;
    private DepartmentPresenter departmentPresenter;
    private Show_InquiryAdaper show_inquiryAdaper;
    private ShowOutAdapter showOutAdapter;
    private ConsultAdapter consultAdapter;

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
                formationListPresenter.reqeust(position, 1, 5);
            }
        });

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

    @OnClick({R2.id.show_banner, R2.id.show_disease, R2.id.show_drugs, R2.id.show_image})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.show_banner) {
        } else if (i == R.id.show_disease) {
            intent(DiseaseActivity.class);
        } else if (i == R.id.show_drugs) {
            intent(DiseaseActivity.class);
        } else if (i == R.id.show_image) {
        }
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
