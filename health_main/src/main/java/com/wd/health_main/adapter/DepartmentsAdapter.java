package com.wd.health_main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.common.bean.FormaBean;
import com.wd.common.bean.KeshiBean;
import com.wd.health_main.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @describe(描述)：com.wd.health_main.adapter
 * @data（日期）: 2019/12/17
 * @time（时间）: 14:32
 * @author（作者）: 闫小康
 * @function(功能):
 **/
public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentsAdapter.ViewHolder> {
    private List<KeshiBean> list = new ArrayList<>();

    @NonNull
    @Override
    public DepartmentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keshi, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView .setText(list.get(position).departmentName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.cheked(list.get(position).id);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.parent_name);

        }
    }

    public void addAll(List<KeshiBean> data) {
        if (data != null) {
            list.addAll(data);
        }
    }
    public interface CheckedListener {
        void cheked(int position);
    }

    CheckedListener listener;

    public void setListener(CheckedListener listener) {
        this.listener = listener;
    }
}
