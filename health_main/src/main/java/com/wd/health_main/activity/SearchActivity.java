package com.wd.health_main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.bean.HomePageSearch;
import com.wd.common.bean.PopularSearch;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDActivity;
import com.wd.common.core.exception.ApiException;
import com.wd.health_main.R;
import com.wd.health_main.R2;
import com.wd.health_main.adapter.PopularSearchAdapter;
import com.wd.health_main.adapter.SearchAdapter;
import com.wd.health_main.presenter.HomePageSearchPresenter;
import com.wd.health_main.presenter.PopularSearchPresenter;

import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends WDActivity {

    @BindView(R2.id.search_back)
    SimpleDraweeView searchBack;
    @BindView(R2.id.search_text)
    TextView searchText;
    @BindView(R2.id.search_view)
    RecyclerView searchView;
    @BindView(R2.id.kylin_view)
    EditText kylinView;
    @BindView(R2.id.search_recy)
    RecyclerView searchRecy;
    private PopularSearchAdapter adapter;
    private HomePageSearchPresenter homePageSearchPresenter;
    private SearchAdapter searchAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }
    @Override
    protected void initView() {
        PopularSearchPresenter popularSearchPresenter = new PopularSearchPresenter(new Popilar());
        popularSearchPresenter.reqeust();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        searchView.setLayoutManager(gridLayoutManager);
        adapter = new PopularSearchAdapter();
        searchView.setAdapter(adapter);
        searchAdapter = new SearchAdapter();
        homePageSearchPresenter = new HomePageSearchPresenter(new Homepage());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        searchRecy.setLayoutManager(linearLayoutManager);
        searchRecy.setAdapter(searchAdapter);
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

    @OnClick({R2.id.search_back, R2.id.search_text})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.search_back) {
            finish();
        } else if (i == R.id.search_text) {
            String trim = kylinView.getText().toString().trim();
            homePageSearchPresenter.reqeust(trim);
        }
    }

    private class Popilar implements DataCall<List<PopularSearch>> {
        @Override
        public void success(List<PopularSearch> data, Object... args) {
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class Homepage implements DataCall<HomePageSearch> {
        @Override
        public void success(HomePageSearch data, Object... args) {
            searchAdapter.addAll(Arrays.asList(data));
            searchAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
