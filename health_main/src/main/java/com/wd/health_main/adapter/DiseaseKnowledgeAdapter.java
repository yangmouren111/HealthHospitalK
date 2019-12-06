package com.wd.health_main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.common.bean.KnowledgeBean;
import com.wd.health_main.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:  2019-12-06
 * Author:  杨世博
 * Description:
 */
public class DiseaseKnowledgeAdapter extends RecyclerView.Adapter {
    private List<KnowledgeBean> list = new ArrayList<>();

    public void addAll(List<KnowledgeBean> data) {
        if (data != null) {
            list.addAll(data);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_disease_konwledge, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewholder) holder).dis_kon_text1.setText(list.get(position).pathology);
        ((MyViewholder) holder).dis_kon_text2.setText(list.get(position).symptom);
        ((MyViewholder) holder).dis_kon_text3.setText(list.get(position).benefitTaboo);
        ((MyViewholder) holder).dis_kon_text5.setText(list.get(position).chineseMedicineTreatment);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView  dis_kon_text1, dis_kon_text2, dis_kon_text3, dis_kon_text4, dis_kon_text5;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            dis_kon_text1 = itemView.findViewById(R.id.dis_kon_text1);
            dis_kon_text2 = itemView.findViewById(R.id.dis_kon_text2);
            dis_kon_text3 = itemView.findViewById(R.id.dis_kon_text3);
            dis_kon_text4 = itemView.findViewById(R.id.dis_kon_text4);
            dis_kon_text5 = itemView.findViewById(R.id.dis_kon_text5);


        }
    }
}
