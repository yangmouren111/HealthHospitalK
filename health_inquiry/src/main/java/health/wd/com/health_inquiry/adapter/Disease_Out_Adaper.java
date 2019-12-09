package health.wd.com.health_inquiry.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.common.bean.DepartmentBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import health.wd.com.health_inquiry.R;

/**
 * Time:  2019-12-05
 * Author:  杨世博
 * Description:
 */
public class Disease_Out_Adaper extends RecyclerView.Adapter {
    private List<DepartmentBean> list = new ArrayList<>();

    public void addAll(List<DepartmentBean> data) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_out_adaper, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyViewholder) holder).parent_name.setText(list.get(position).departmentName);
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

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView parent_name;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            parent_name = itemView.findViewById(R.id.parent_name);

        }
    }
}
