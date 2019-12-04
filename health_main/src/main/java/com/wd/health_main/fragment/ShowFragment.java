package com.wd.health_main.fragment;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;
import com.wd.common.bean.Banner;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDFragment;
import com.wd.common.core.exception.ApiException;
import com.wd.health_main.R;
import com.wd.health_main.R2;
import com.wd.health_main.presenter.BannerPresenter;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

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
        BannerPresenter bannerPresenter = new BannerPresenter(new banner());
        bannerPresenter.reqeust();

    }
    @OnClick({R2.id.show_banner, R2.id.show_disease, R2.id.show_drugs, R2.id.show_inner, R2.id.show_eye, R2.id.show_bone, R2.id.show_little, R2.id.show_pass, R2.id.show_skin, R2.id.show_ear, R2.id.show_spirit, R2.id.show_image, R2.id.show_tab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.show_banner:
                break;
            case R2.id.show_disease:
                break;
            case R2.id.show_drugs:
                break;
            case R2.id.show_inner:
                break;
            case R2.id.show_eye:
                break;
            case R2.id.show_bone:
                break;
            case R2.id.show_little:
                break;
            case R2.id.show_pass:
                break;
            case R2.id.show_skin:
                break;
            case R2.id.show_ear:
                break;
            case R2.id.show_spirit:
                break;
            case R2.id.show_image:
                break;
            case R2.id.show_tab:
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
}
