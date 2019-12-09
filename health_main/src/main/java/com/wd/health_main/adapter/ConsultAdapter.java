package com.wd.health_main.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    private FormaBean formaBean;

    public void addAll(List<FormaBean> data) {
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
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_consult, parent, false);
            return new MyViewholder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_consult_inner, parent, false);
            return new ThreePicViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int viewType = holder.getItemViewType();
        if (viewType == 0) {
            ((MyViewholder) holder).consult_name.setText(list.get(position).source);
            ((MyViewholder) holder).consult_content.setText(list.get(position).title);
            // String[] images = circle.getImage().split(";");
            ((MyViewholder) holder).consult_image.setImageURI(Uri.parse(list.get(position).thumbnail));
            try {
                ((MyViewholder) holder).consult_time.setText(DateUtils.dateFormat(new Date(list.get(position).releaseTime), DateUtils.MINUTE_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            ThreePicViewHolder viewHolder = (ThreePicViewHolder) holder;
            FormaBean news = list.get(position);
            viewHolder.tvContent.setText(news.title);
            viewHolder.tvSource.setText(news.source);
            try {
                viewHolder.tvTime.setText(DateUtils.dateFormat(new Date(list.get(position).releaseTime), DateUtils.MINUTE_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String image = list.get(position).thumbnail;
            String[] split = image.split(";");
            Glide.with(holder.itemView.getContext()).load(split[0]).into(viewHolder.ivPic1);
            Glide.with(holder.itemView.getContext()).load(split[1]).into(viewHolder.ivPic2);
            Glide.with(holder.itemView.getContext()).load(split[2]).into(viewHolder.ivPic3);
        }
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

    @Override
    public int getItemViewType(int position) {
        formaBean = list.get(position);
        String image = list.get(position).thumbnail;
        String[] split = image.split(";");
        boolean isThreePic = split.length == 3;
        int viewType = isThreePic ? 1 : 0;
        return viewType;
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

    static class ThreePicViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPic1;
        ImageView ivPic2;
        ImageView ivPic3;
        TextView tvContent;
        TextView tvSource;
        TextView tvTime;

        public ThreePicViewHolder(View view) {
            super(view);
            ivPic1 = view.findViewById(R.id.iv_pic1);
            ivPic2 = view.findViewById(R.id.iv_pic2);
            ivPic3 = view.findViewById(R.id.iv_pic3);
            tvContent = view.findViewById(R.id.tv_content);
            tvSource = view.findViewById(R.id.tv_source);
            tvTime = view.findViewById(R.id.tv_time);
        }
    }

}