package com.wd.health_main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.common.bean.HomePageSearch;
import com.wd.common.bean.PopularSearch;
import com.wd.health_main.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:  2019-12-11
 * Author:  杨世博
 * Description:
 */
public class SearchAdapter extends RecyclerView.Adapter {
    private List<HomePageSearch> list = new ArrayList<>();

    public void addAll(List<HomePageSearch> data) {
        if (data != null) {
            list.addAll(data);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewholder) holder).search_adapter_name.setText(list.get(position).doctorSearchVoList.get(position).doctorName);
//        ((MyViewholder) holder).search_adapter_name3.setText(list.get(position).diseaseSearchVoList.get(position).diseaseName);
//        ((MyViewholder) holder).search_adapter_name2.setText(list.get(position).drugsSearchVoList.get(position).drugsName);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView search_adapter_name,search_adapter_name2,search_adapter_name3;


        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            search_adapter_name = itemView.findViewById(R.id.search_adapter_name);
//            search_adapter_name2 = itemView.findViewById(R.id.search_adapter_name2);
//            search_adapter_name3 = itemView.findViewById(R.id.search_adapter_name3);

        }
    }
}
