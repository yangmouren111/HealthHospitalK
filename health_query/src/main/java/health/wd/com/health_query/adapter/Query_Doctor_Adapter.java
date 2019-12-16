package health.wd.com.health_query.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.common.bean.DepartmentBean;
import com.wd.common.bean.FindDoctorListBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import health.wd.com.health_query.R;

/**
 * Time:  2019-12-12
 * Author:  杨世博
 * Description:
 */
public class Query_Doctor_Adapter extends RecyclerView.Adapter {
    private List<FindDoctorListBean> list = new ArrayList<>();

    public void addAll(List<FindDoctorListBean> data) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_query_doctor, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyViewholder) holder).query_adapter_yisheng.setText(list.get(position).doctorName);
        String imagePic = list.get(position).imagePic;
        ((MyViewholder) holder).query_adapter_image.setImageURI(Uri.parse(imagePic));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(position).doctorId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView query_adapter_yisheng;
        ImageView query_adapter_image;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            query_adapter_yisheng = itemView.findViewById(R.id.query_adapter_yisheng);
            query_adapter_image = itemView.findViewById(R.id.query_adapter_image);


        }
    }
}
