package com.wd.health_main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.common.bean.DuiyingBean;
import com.wd.common.util.DateUtils;
import com.wd.health_main.R;
import com.wd.health_main.fragment.CircleFragment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @describe(描述)：com.wd.health_main.adapter
 * @data（日期）: 2019/12/17
 * @time（时间）: 16:05
 * @author（作者）: 闫小康
 * @function(功能):
 **/
public class SocietyAdapter extends RecyclerView.Adapter<SocietyAdapter.ViewHolder> {
    private List<DuiyingBean> list = new ArrayList<>();

    @NonNull
    @Override
    public SocietyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.society_text, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.detail.setText(list.get(position).detail);
        holder.title.setText(list.get(position).title);
        try {
            holder.liebiao_time.setText(DateUtils.dateFormat(new Date(list.get(position).releaseTime),DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView detail;
        private final TextView title;
        private final TextView liebiao_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            detail = itemView.findViewById(R.id.detail);
            title = itemView.findViewById(R.id.title);
            liebiao_time = itemView.findViewById(R.id.liebiao_time);

        }
    }

    public void addAll(List<DuiyingBean> data) {
        if (data != null) {
            list.addAll(data);
        }

    }

    public void clear() {
        list.clear();
    }
}
