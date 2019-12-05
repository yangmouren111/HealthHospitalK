package com.wd.health_main.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;
import com.wd.common.bean.Banner;
import com.wd.common.bean.FormaBean;
import com.wd.common.bean.PlateBean;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDFragment;
import com.wd.common.core.exception.ApiException;
import com.wd.common.util.Constant;
import com.wd.health_main.R;
import com.wd.health_main.R2;
import com.wd.health_main.activity.BoneActivity;
import com.wd.health_main.activity.DiseaseActivity;
import com.wd.health_main.activity.DrugsActivity;
import com.wd.health_main.activity.EarActivity;
import com.wd.health_main.activity.EyeActivity;
import com.wd.health_main.activity.InnerActivity;
import com.wd.health_main.activity.LittleActivity;
import com.wd.health_main.activity.PassActivity;
import com.wd.health_main.activity.SkinActivity;
import com.wd.health_main.activity.SpiritActivity;
import com.wd.health_main.adapter.FragAdapter;
import com.wd.health_main.presenter.BannerPresenter;
import com.wd.health_main.presenter.FormationListPresenter;
import com.wd.health_main.presenter.PlateListPresenter;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Time:  2019-12-04
 * Author:  杨世博
 * Description:
 */
public class ShowFragment extends WDFragment {

    @BindView(R2.id.show_banner)
    MZBannerView showBanner;
    @BindView(R2.id.show_disease)
    ImageView showDisease;
    @BindView(R2.id.show_drugs)
    ImageView showDrugs;
    @BindView(R2.id.show_inner)
    ImageView showInner;
    @BindView(R2.id.show_eye)
    ImageView showEye;
    @BindView(R2.id.show_bone)
    ImageView showBone;
    @BindView(R2.id.show_little)
    ImageView showLittle;
    @BindView(R2.id.show_pass)
    ImageView showPass;
    @BindView(R2.id.show_skin)
    ImageView showSkin;
    @BindView(R2.id.show_ear)
    ImageView showEar;
    @BindView(R2.id.show_spirit)
    ImageView showSpirit;
    @BindView(R2.id.show_image)
    ImageView showImage;
    @BindView(R2.id.show_tab)
    TabLayout showTab;
    @BindView(R2.id.show_view)
    ViewPager showView;
    private FormationListPresenter formationListPresenter;
    private PlateListPresenter plateListPresenter;
    private BannerPresenter bannerPresenter;

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
        plateListPresenter = new PlateListPresenter(new plate());
        plateListPresenter.reqeust();
//        formationListPresenter = new FormationListPresenter(new format());
//        formationListPresenter.reqeust();
//
//        List<Fragment> list = new ArrayList<>();
//
//       // list.add(new VideoFragment());
//
//        FragAdapter adapter = new FragAdapter(getChildFragmentManager(), list);
//        showView.setAdapter(adapter);
//        showTab.setupWithViewPager(showView);


    }


    @SuppressLint("InvalidR2Usage")
    @OnClick({R2.id.show_banner, R2.id.show_disease, R2.id.show_drugs, R2.id.show_inner, R2.id.show_eye, R2.id.show_bone, R2.id.show_little, R2.id.show_pass, R2.id.show_skin, R2.id.show_ear, R2.id.show_spirit, R2.id.show_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.show_banner:
                break;
            case R2.id.show_disease:
                intentByRouter(Constant.ACTIVITY_URL_DISE);
                break;
            case R2.id.show_drugs:
               intent(DrugsActivity.class);
                break;
            case R2.id.show_inner:
                Intent intent3 = new Intent(getActivity(), InnerActivity.class);
                startActivity(intent3);
                break;
            case R2.id.show_eye:
                Intent intent4 = new Intent(getActivity(), EyeActivity.class);
                startActivity(intent4);
                break;
            case R2.id.show_bone:
                Intent intent10 = new Intent(getActivity(), BoneActivity.class);
                startActivity(intent10);
                break;
            case R2.id.show_little:
                Intent intent5 = new Intent(getActivity(), LittleActivity.class);
                startActivity(intent5);
                break;
            case R2.id.show_pass:
                Intent intent6 = new Intent(getActivity(), PassActivity.class);
                startActivity(intent6);
                break;
            case R2.id.show_skin:
                Intent intent7 = new Intent(getActivity(), SkinActivity.class);
                startActivity(intent7);
                break;
            case R2.id.show_ear:
                Intent intent8 = new Intent(getActivity(), EarActivity.class);
                startActivity(intent8);
                break;
            case R2.id.show_spirit:
                Intent intent9 = new Intent(getActivity(), SpiritActivity.class);
                startActivity(intent9);
                break;
            case R2.id.show_image:
                break;
        }
    }

    class BannerViewHolder implements MZViewHolder<Banner> {
        private SimpleDraweeView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.layout_banner, null);
            mImageView = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Banner data) {
            // 数据绑定
            mImageView.setImageURI(Uri.parse(data.imageUrl));
        }
    }

    private class banner implements DataCall<List<Banner>> {
        @Override
        public void success(List<Banner> data, Object... args) {
            showBanner.setIndicatorVisible(false);
            showBanner.setPages(data, new MZHolderCreator<BannerViewHolder>() {
                @Override
                public BannerViewHolder createViewHolder() {
                    return new BannerViewHolder();
                }
            });
            showBanner.start();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class plate implements DataCall<List<PlateBean>> {

        @Override
        public void success(List<PlateBean> data, Object... args) {

           // showTab.getTabAt(0).setText(data.get(0).name);
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class format implements DataCall<List<FormaBean>> {
        @Override
        public void success(List<FormaBean> data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
