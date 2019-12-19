package health.wd.com.health_query.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.bean.DepartmentBean;
import com.wd.common.bean.FindDoctorListBean;
import com.wd.common.bean.InformaBean;
import com.wd.common.bean.Result;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDActivity;
import com.wd.common.core.exception.ApiException;
import com.wd.common.util.Constant;

import java.text.DecimalFormat;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import health.wd.com.health_query.R;
import health.wd.com.health_query.R2;
import health.wd.com.health_query.adapter.QueryAdapter;
import health.wd.com.health_query.adapter.Query_Doctor_Adapter;
import health.wd.com.health_query.core.CustomDialog;
import health.wd.com.health_query.presenter.ConsultDoctorPresenter;
import health.wd.com.health_query.presenter.FindDoctorListPresenter;
import health.wd.com.health_query.presenter.QueryPresenter;

public class QueryActivity extends WDActivity {

    @BindView(R2.id.query_view)
    RecyclerView queryView;
    @BindView(R2.id.tv_zh)
    TextView tvZh;
    @BindView(R2.id.tv_hp)
    TextView tvHp;
    @BindView(R2.id.tv_zxs)
    TextView tvZxs;
    @BindView(R2.id.tv_price)
    TextView tvPrice;
    @BindView(R2.id.sdv_pic)
    SimpleDraweeView sdvPic;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.tv_jobTitle)
    TextView tvJobTitle;
    @BindView(R2.id.tv_inauguralHospital)
    TextView tvInauguralHospital;
    @BindView(R2.id.tv_praiseNum)
    TextView tvPraiseNum;
    @BindView(R2.id.tv_serverNum)
    TextView tvServerNum;
    @BindView(R2.id.tv_servicePrice)
    TextView tvServicePrice;
    @BindView(R2.id.tv_ask)
    TextView tvAsk;
    @BindView(R2.id.sdv_left)
    SimpleDraweeView sdvLeft;
    @BindView(R2.id.sdv_right)
    SimpleDraweeView sdvRight;
    @BindView(R2.id.rv_doctor)
    RecyclerView rvDoctor;
    private int position1;
    private QueryPresenter queryPresenter;
    private QueryAdapter queryAdapter;
    private int deptId;
    private FindDoctorListPresenter findDoctorListPresenter;
    private Query_Doctor_Adapter query_doctor_adapter;
    private ConsultDoctorPresenter consultDoctorPresenter;
    private double result;
    private int servicepr;
    private PopupWindow popupWindow;
    private CustomDialog customDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_query;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        queryView.setLayoutManager(linearLayoutManager);
        queryAdapter = new QueryAdapter();
        queryView.setAdapter(queryAdapter);
        queryPresenter = new QueryPresenter(new Query());
        queryPresenter.reqeust();
        queryAdapter.setOnItemClickListener(new QueryAdapter.OnItemClickListener() {

            @Override
            public void onClick(int position) {
                position1 = position;
                findDoctorListPresenter.reqeust(position1, 4, 1, 1, 5);
            }
        });
        findDoctorListPresenter = new FindDoctorListPresenter(new FindDoctor());
        findDoctorListPresenter.reqeust(11, 1, 1, 1, 5);
        consultDoctorPresenter = new ConsultDoctorPresenter(new Consult());

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

    @OnClick({R2.id.tv_zh, R2.id.tv_hp, R2.id.tv_zxs, R2.id.tv_price, R2.id.tv_ask})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.tv_zh) {
            findDoctorListPresenter.reqeust(position1, 1, 1, 1, 5);
        } else if (i == R.id.tv_hp) {
            findDoctorListPresenter.reqeust(position1, 2, 2, 1, 5);
        } else if (i == R.id.tv_zxs) {
            findDoctorListPresenter.reqeust(position1, 3, 3, 1, 5);
        } else if (i == R.id.tv_price) {
            findDoctorListPresenter.reqeust(position1, 4, 4, 1, 5);
        } else if (i == R.id.tv_ask) {
            // consultDoctorPresenter.reqeust("10","10",position1);
            showPopWindow();
        }
    }

    private void showPopWindow() {
//        //找到pop弹窗布局
//       // View view = LayoutInflater.from(QueryActivity.this).inflate(R.layout.pop_advisory, null);
//        View view = getLayoutInflater().inflate(R.layout.pop_advisory, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
//
//        builder.setMessage("本次咨询将扣除500H币!").setPositiveButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        }).setNegativeButton("去咨询", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.create().show();
//
//        //找子布局控件
//        TextView but = view.findViewById(R.id.pop_but);
//        TextView qu = view.findViewById(R.id.pop_qu);
//        View view = getLayoutInflater().inflate(R.layout.pop_advisory, null);
//        customDialog = new CustomDialog(this, 0, 0, view, R.style.DialogTheme);
//        customDialog.setCancelable(true);
//        customDialog.show();
        final CustomDialog dialog = new CustomDialog(QueryActivity.this);
        dialog.setMessage("本次咨询将扣除500H币!")
//                .setTitle("系统提示")
                .setSingle(true).setOnClickBottomListener(new CustomDialog.OnClickBottomListener() {
            @Override
            public void onPositiveClick() {
                dialog.dismiss();
                Toast.makeText(QueryActivity.this,"xxxx",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNegtiveClick() {
                dialog.dismiss();
                Toast.makeText(QueryActivity.this,"ssss",Toast.LENGTH_SHORT).show();
            }
        }).show();

    }

    private class Query implements DataCall<List<DepartmentBean>> {
        @Override
        public void success(List<DepartmentBean> data, Object... args) {
            queryAdapter.addAll(data);
            queryAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class FindDoctor implements DataCall<List<FindDoctorListBean>> {
        @Override
        public void success(List<FindDoctorListBean> data, Object... args) {
            sdvPic.setImageURI(data.get(0).imagePic);
            tvName.setText(data.get(0).doctorName);
            tvInauguralHospital.setText(data.get(0).inauguralHospital);
            tvJobTitle.setText(data.get(0).jobTitle);
            tvPraiseNum.setText("好评率   " + data.get(0).praise);
            tvServerNum.setText("服务患者数   " + data.get(0).serverNum);
            tvServicePrice.setText(data.get(0).servicePrice + "H币/次");
            servicepr = data.get(0).servicePrice;
            query_doctor_adapter = new Query_Doctor_Adapter();
            rvDoctor.setAdapter(query_doctor_adapter);
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(QueryActivity.this);
            linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvDoctor.setLayoutManager(linearLayoutManager1);
            query_doctor_adapter.setOnItemClickListener(new Query_Doctor_Adapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    findDoctorListPresenter.reqeust(position, 4, 0, 1, 5);
                }
            });
            query_doctor_adapter.addAll(data);
            query_doctor_adapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class Consult implements DataCall<Result> {


        @Override
        public void success(Result data, Object... args) {
//            if (data.getStatus().equals("0000")) {
//                DecimalFormat decimalFormat = new DecimalFormat("######0.00");
//                result = (double) data.getResult();
//                if (result >=servicepr){
//                    //点击判断弹出pop本次将扣除
//                    showPopWindow();
//                }else {
//                    //H币不足
//                    showqueWindow();
//                }
//            }else  if (data.getStatus().equals("9999")){
//                //请先登录
//                showloginWindow();
//            }
            if (data.getStatus().equals("0000")) {
                showPopWindow();
            } else {
                Toast.makeText(QueryActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private void showloginWindow() {
//        //找到pop弹窗布局
//       // View view = LayoutInflater.from(QueryActivity.this).inflate(R.layout.pop_login, null);
//        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        popupWindow.setContentView(view);
//        //设置高度
//        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
//        popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        //找子布局控件
//        TextView but = view.findViewById(R.id.pop_but_login);
//        TextView qu = view.findViewById(R.id.pop_qu_login);
        //去咨询
//        but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //intentByRouter(Constant.ACTIVITY_URL_MY_LOG);
//                popupWindow.dismiss();
//            }
//        });
//        //取消
//        qu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//        //activity的布局
//        View rootView = LayoutInflater.from(QueryActivity.this).inflate(R.layout.activity_query, null);
//        //位置
//        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

    private void showqueWindow() {
        //找到pop弹窗布局
        View view = LayoutInflater.from(QueryActivity.this).inflate(R.layout.pop_que, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(view);
        //设置高度
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        //找子布局控件
        TextView but = view.findViewById(R.id.pop_but_que);
        TextView qu = view.findViewById(R.id.pop_qu_que);
        //去咨询
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //取消
        qu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //activity的布局
        View rootView = LayoutInflater.from(QueryActivity.this).inflate(R.layout.activity_query, null);
        //位置
        popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }
}
