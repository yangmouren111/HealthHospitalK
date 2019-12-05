package com.wd.health_main.fragment.fragment_consult;

import android.widget.RelativeLayout;

import com.wd.common.core.WDFragment;
import com.wd.health_main.R;
import com.wd.health_main.R2;

import butterknife.BindView;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description: 健身减肥
 */
public class ReduceFragment extends WDFragment {
    @BindView(R2.id.reduce_view)
    RelativeLayout reduceView;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_reduce;
    }

    @Override
    protected void initView() {

    }
}
