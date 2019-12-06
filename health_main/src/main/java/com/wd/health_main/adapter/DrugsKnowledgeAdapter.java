package com.wd.health_main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.common.bean.DrugsledgeBean;
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
public class DrugsKnowledgeAdapter extends RecyclerView.Adapter {
    private List<DrugsledgeBean> list = new ArrayList<>();

    public void addAll(List<DrugsledgeBean> data) {
        if (data != null) {
            list.addAll(data);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_drugs_konwledge, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewholder) holder).kon_text1.setText(list.get(position).component);
        ((MyViewholder) holder).kon_text2.setText(list.get(position).taboo);
        ((MyViewholder) holder).kon_text3.setText(list.get(position).effect);
        ((MyViewholder) holder).kon_text4.setText(list.get(position).usage);
        ((MyViewholder) holder).kon_text5.setText(list.get(position).shape);
        ((MyViewholder) holder).kon_text6.setText(list.get(position).packing);
        ((MyViewholder) holder).kon_text7.setText(list.get(position).sideEffects);
        ((MyViewholder) holder).kon_text8.setText(list.get(position).storage);
        ((MyViewholder) holder).kon_text9.setText(list.get(position).mindMatter);
        ((MyViewholder) holder).kon_text10.setText(list.get(position).approvalNumber);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView  kon_text1, kon_text2, kon_text3, kon_text4, kon_text5,kon_text6,kon_text7,kon_text8,kon_text9,kon_text10;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            kon_text1 = itemView.findViewById(R.id.kon_text1);
            kon_text2 = itemView.findViewById(R.id.kon_text2);
            kon_text3 = itemView.findViewById(R.id.kon_text3);
            kon_text4 = itemView.findViewById(R.id.kon_text4);
            kon_text5 = itemView.findViewById(R.id.kon_text5);
            kon_text6 = itemView.findViewById(R.id.kon_text6);
            kon_text7 = itemView.findViewById(R.id.kon_text7);
            kon_text8 = itemView.findViewById(R.id.kon_text8);
            kon_text9 = itemView.findViewById(R.id.kon_text9);
            kon_text10 = itemView.findViewById(R.id.kon_text10);


        }
    }
}
