package com.wd.health_main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.common.bean.CategroyBean;
import com.wd.common.bean.DepartmentBean;
import com.wd.health_main.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description:
 */
public class Disease_Child_Adaper extends RecyclerView.Adapter {
    private List<CategroyBean> list = new ArrayList<>();

    public void addAll(List<CategroyBean> data) {
        if (data != null) {
            list.addAll(data);
        }
    }
    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);

        void onClick(String name);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_child_adaper, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyViewholder) holder).child_name.setText(list.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               listener.onClick(list.get(position).id);
               listener.onClick(list.get(position).name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView child_name;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            child_name = itemView.findViewById(R.id.child_name);

        }
    }
}
