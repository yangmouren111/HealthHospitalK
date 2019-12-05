package com.wd.health_main.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.common.bean.FormaBean;
import com.wd.common.util.DateUtils;
import com.wd.health_main.R;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description: 健康咨询适配器
 */
public class ConsultAdapter extends RecyclerView.Adapter {
    private List<FormaBean> list = new ArrayList<>();

    public void addAll(List<FormaBean> data) {
        if (data != null) {
            list.addAll(data);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_consult, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewholder) holder).consult_name.setText(list.get(position).source);
        ((MyViewholder) holder).consult_content.setText(list.get(position).title);
        // String[] images = circle.getImage().split(",");
        ((MyViewholder) holder).consult_image.setImageURI(Uri.parse(list.get(position).thumbnail));
        try {
            ((MyViewholder) holder).consult_time.setText(DateUtils.dateFormat(new Date(list.get(position).releaseTime), DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        ImageView consult_image;
        TextView consult_name, consult_content, consult_time;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            consult_image = itemView.findViewById(R.id.consult_image);
            consult_name = itemView.findViewById(R.id.consult_name);
            consult_content = itemView.findViewById(R.id.consult_content);
            consult_time = itemView.findViewById(R.id.consult_time);
        }
    }
}