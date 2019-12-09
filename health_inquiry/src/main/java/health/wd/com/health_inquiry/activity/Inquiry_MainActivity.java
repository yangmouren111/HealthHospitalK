package health.wd.com.health_inquiry.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.wd.common.bean.DepartmentBean;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDActivity;
import com.wd.common.core.exception.ApiException;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import health.wd.com.health_inquiry.R;
import health.wd.com.health_inquiry.R2;
import health.wd.com.health_inquiry.adapter.Disease_Out_Adaper;
import health.wd.com.health_inquiry.presenter.DepartPresenter;

public class Inquiry_MainActivity extends WDActivity {


    @BindView(R2.id.mian_recy)
    RecyclerView mianRecy;
    private DepartPresenter departmentPresenter;
    private Disease_Out_Adaper disease_out_adaper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inquiry__main;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mianRecy.setLayoutManager(linearLayoutManager);
        disease_out_adaper = new Disease_Out_Adaper();
        mianRecy.setAdapter(disease_out_adaper);
        departmentPresenter = new DepartPresenter(new depart());
        departmentPresenter.reqeust();
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

    private class depart implements DataCall<List<DepartmentBean>> {
        @Override
        public void success(List<DepartmentBean> data, Object... args) {
            disease_out_adaper.addAll(data);
            disease_out_adaper.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
