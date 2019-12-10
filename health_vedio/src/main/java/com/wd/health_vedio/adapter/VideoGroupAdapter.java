package com.wd.health_vedio.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.common.bean.VideoGroup;
import com.wd.health_vedio.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @describe(描述)：com.wd.health_vedio.adapter
 * @data（日期）: 10:2019/12/5
 * @time（时间）: 10:54
 * @author（作者）: 盖磊
 **/
public class VideoGroupAdapter extends RecyclerView.Adapter<VideoGroupAdapter.VideoGroupViewHolder>{

    List<VideoGroup> list = new ArrayList<>();
    HashMap<String, Boolean> states = new HashMap<String, Boolean>();

    public void addAll(List<VideoGroup> groups){
        if (groups!=null){
         list.addAll(groups);
        }
    }

    public void clear(){
        list.clear();
    }

    @NonNull
    @Override
    public VideoGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_group_item, parent, false);
        return new VideoGroupViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoGroupViewHolder holder, final int position) {
        final VideoGroup group = list.get(position);
        holder.mText.setText(group.name);
        holder.mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoGroupItemOnClickListener!= null)
                    videoGroupItemOnClickListener.onClick(v,group.id);
                // 重置，确保最多只有一项被选中
                for (String key : states.keySet()) {
                    states.put(key, false);
                }
                states.put(String.valueOf(position), true);
                VideoGroupAdapter.this.notifyDataSetChanged();
            }
        });
        boolean res = false;
        if (states.get(String.valueOf(position)) == null|| states.get(String.valueOf(position)) == false) {
            res = false;
            states.put(String.valueOf(position), false);
        } else
            res = true;
        holder.mText.setChecked(res);

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    class VideoGroupViewHolder extends RecyclerView.ViewHolder {
        RadioButton mText;
        LinearLayout mLayout;
        public VideoGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.video_group_text);
            mLayout = itemView.findViewById(R.id.video_group_layout);

        }
    }

    private VideoGroupItemOnClickListener videoGroupItemOnClickListener;

    public void setVideoGroupItemOnClickListener(VideoGroupItemOnClickListener videoGroupItemOnClickListener) {
        this.videoGroupItemOnClickListener = videoGroupItemOnClickListener;
    }

    public interface VideoGroupItemOnClickListener{
        void onClick(View view,int id);
    }

}
