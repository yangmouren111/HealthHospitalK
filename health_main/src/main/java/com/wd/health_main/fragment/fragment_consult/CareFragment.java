package com.wd.health_main.fragment.fragment_consult;

import android.widget.RelativeLayout;

import com.wd.common.core.WDFragment;
import com.wd.health_main.R;
import com.wd.health_main.R2;

import butterknife.BindView;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description: 医疗动态
 */
public class CareFragment extends WDFragment {
    @BindView(R2.id.care_view)
    RelativeLayout careView;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_care;
    }

    @Override
    protected void initView() {

    }
}
