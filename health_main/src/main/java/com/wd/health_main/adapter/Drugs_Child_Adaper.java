package com.wd.health_main.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.common.bean.CategroyBean;
import com.wd.common.bean.DrugsKonwBean;
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
public class Drugs_Child_Adaper extends RecyclerView.Adapter {
    private List<DrugsKonwBean> list = new ArrayList<>();

    public void addAll(List<DrugsKonwBean> data) {
        if (data != null) {
            list.addAll(data);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_drugs_child_adaper, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewholder) holder).drugs_child_name.setText(list.get(position).name);
        ((MyViewholder) holder).drugs_child_image.setImageURI(Uri.parse(list.get(position).picture));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView drugs_child_name;
        ImageView drugs_child_image;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            drugs_child_name = itemView.findViewById(R.id.drugs_child_name);
            drugs_child_image = itemView.findViewById(R.id.drugs_child_image);

        }
    }
}
