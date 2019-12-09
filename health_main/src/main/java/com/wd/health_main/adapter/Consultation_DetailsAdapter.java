package com.wd.health_main.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.net.http.SslError;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wd.common.bean.InformaBean;
import com.wd.common.util.DateUtils;
import com.wd.health_main.R;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        ((MyViewholder) holder).condet_title.setText(list.get(position).title);
        ((MyViewholder) holder).condet_name.setText(list.get(position).source);
        try {
            ((MyViewholder) holder).condet_time.setText(DateUtils.dateFormat(new Date(list.get(position).releaseTime), DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((MyViewholder) holder).condet_context.getSettings(). setJavaScriptEnabled(true);


        ((MyViewholder) holder).condet_context.setWebViewClient(new WebViewClient(){

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });

            //设置图片显示宽度100% 高度自适应
        String varjs = "<script type='text/javascript'> \nwindow.onload = function()\n{var $img = document.getElementsByTagName('img');for(var p in  $img){$img[p].style.width = '100%'; $img[p].style.height ='auto'}}</script>";
        ((MyViewholder) holder).condet_context.loadData(varjs+list.get(position).content, "text/html", "UTF-8");


    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView condet_title, condet_name, condet_time;
        WebView condet_context;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            condet_title = itemView.findViewById(R.id.condet_title);
            condet_name = itemView.findViewById(R.id.condet_name);
            condet_time = itemView.findViewById(R.id.condet_time);
            condet_context = itemView.findViewById(R.id.condet_context);
        }
    }
}
