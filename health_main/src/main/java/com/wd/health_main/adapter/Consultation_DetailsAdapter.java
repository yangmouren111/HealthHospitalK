package com.wd.health_main.adapter;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.common.bean.FormaBean;
import com.wd.common.bean.InformaBean;
import com.wd.common.util.DateUtils;
import com.wd.health_main.R;
import com.wd.health_main.activity.Consultation_DetailsActivity;
import com.wd.health_main.core.HtmlUtils;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:  2019-12-06
 * Author:  杨世博
 * Description:
 */
public class Consultation_DetailsAdapter extends RecyclerView.Adapter {
    private List<InformaBean> list = new ArrayList<>();
    private String img;

    public void addAll(List<InformaBean> data) {
        if (data != null) {
            list.addAll(data);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_consultation__details, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewholder) holder).condet_title.setText(list.get(position).title);
        ((MyViewholder) holder).condet_name.setText(list.get(position).source);
        try {
            ((MyViewholder) holder).condet_time.setText(DateUtils.dateFormat(new Date(list.get(position).releaseTime), DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }


       ((MyViewholder) holder).condet_context.setText(Html.fromHtml(list.get(position).content));

     }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView condet_title, condet_name, condet_time, condet_context;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            condet_title = itemView.findViewById(R.id.condet_title);
            condet_name = itemView.findViewById(R.id.condet_name);
            condet_time = itemView.findViewById(R.id.condet_time);
            condet_context = itemView.findViewById(R.id.condet_context);
        }
    }
}
