package com.wd.health_main.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
                listener.onClick(position);
                notifyDataSetChanged();
            }
        });
        if (position == getmPosition()) {
            holder.itemView.setBackgroundColor(Color.WHITE);
            ((MyViewholder) holder).drugs_parent_view.setVisibility(View.VISIBLE);
        }else{
//            否则的话就全白色初始化背景
            holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.rec_color));
            ((MyViewholder) holder).drugs_parent_view.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView drugs_parent_name;
       View drugs_parent_view;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            drugs_parent_name = itemView.findViewById(R.id.drugs_parent_name);
            drugs_parent_view = itemView.findViewById(R.id.drugs_parent_view);

        }
    }
    private  int mPosition;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }
}
