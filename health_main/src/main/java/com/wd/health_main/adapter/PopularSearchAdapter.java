package com.wd.health_main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.common.bean.DrugsKonwBean;
import com.wd.common.bean.PopularSearch;
import com.wd.health_main.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:  2019-12-09
 * Author:  杨世博
 * Description:
 */
public class PopularSearchAdapter extends RecyclerView.Adapter {
    private List<PopularSearch> list = new ArrayList<>();

    public void addAll(List<PopularSearch> data) {
        if (data != null) {
            list.addAll(data);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_popular, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ((MyViewholder) holder).popular_text.setText(list.get(position).name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView popular_text;


        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            popular_text = itemView.findViewById(R.id.popular_text);

        }
    }
}
