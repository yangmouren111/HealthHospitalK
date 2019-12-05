package com.wd.health_main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.common.bean.CategroyBean;
import com.wd.common.bean.PlateBean;
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
public class ShowOutAdapter extends RecyclerView.Adapter {
    private List<PlateBean> list = new ArrayList<>();

    public void addAll(List<PlateBean> data) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_show_out, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
     MyViewholder myViewholder  = (MyViewholder) holder;
     myViewholder.show_name_adapter.setText(list.get(position).name);
     myViewholder.itemView.setOnClickListener(new View.OnClickListener() {
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

        TextView show_name_adapter;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            show_name_adapter = itemView.findViewById(R.id.show_name_adapter);

        }
    }
}
