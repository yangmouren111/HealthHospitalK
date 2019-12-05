package com.wd.health_main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.common.bean.DepartmentBean;
import com.wd.common.bean.DrugsCateBean;
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
public class Drugs_Parent_Adaper extends RecyclerView.Adapter {
    private List<DrugsCateBean> list = new ArrayList<>();

    public void addAll(List<DrugsCateBean> data) {
        if (data != null) {
            list.addAll(data);
        }
    }

    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_drugs_parent_adaper, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyViewholder) holder).drugs_parent_name.setText(list.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(position).id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView drugs_parent_name;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            drugs_parent_name = itemView.findViewById(R.id.drugs_parent_name);

        }
    }
}
