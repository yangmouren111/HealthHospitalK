package com.wd.health_main.fragment.fragment_consult;

import android.widget.RelativeLayout;

import com.wd.common.core.WDFragment;
import com.wd.health_main.R;
import com.wd.health_main.R2;

import butterknife.BindView;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description: 健康要闻
 */
public class NewsFragment extends WDFragment {
    @BindView(R2.id.news_view)
    RelativeLayout newsView;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_news;
    }

    @Override
    protected void initView() {

    }
}
