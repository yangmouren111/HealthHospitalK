package com.wd.health_main.fragment.fragment_consult;

import android.widget.RelativeLayout;

import com.wd.common.core.WDFragment;
import com.wd.health_main.R;
import com.wd.health_main.R2;

import butterknife.BindView;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description: 养生美容
 */
public class CosmFragment extends WDFragment {
    @BindView(R2.id.cosm_view)
    RelativeLayout cosmView;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_cosm;
    }

    @Override
    protected void initView() {

    }
}
