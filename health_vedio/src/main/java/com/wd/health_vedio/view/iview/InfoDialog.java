package com.wd.health_vedio.view.iview;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wd.health_vedio.R;

/**
 * @describe(描述)：com.wd.health_vedio.view.iview
 * @data（日期）: 14:2019/12/9
 * @time（时间）: 14:47
 * @author（作者）: 盖磊
 **/
public class InfoDialog extends Dialog {
    public InfoDialog(@NonNull Context context) {
        super(context);
    }

    public InfoDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected InfoDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {

        private View inflate;
        private InfoDialog infoDialog;
        private TextView mTitle;
        private Button mBack;
        private Button mBuy;
        private View.OnClickListener mButtonClickListener;

        public Builder(Context context) {
            infoDialog = new InfoDialog(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflate = inflater.inflate(R.layout.video_buy_dialog, null, false);
            infoDialog.addContentView(inflate, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            mTitle = inflate.findViewById(R.id.dialo_title);
            mBack = inflate.findViewById(R.id.dialo_back);
            mBuy = inflate.findViewById(R.id.dialo_buy);

        }

        public Builder setTitle(@NonNull String title) {
            mTitle.setText(title);
            mTitle.setVisibility(View.VISIBLE);
            return this;
        }

        /**
         * 85          * 设置按钮文字和监听
         * 86
         */
        public Builder setButton(@NonNull  View.OnClickListener listener) {
            mButtonClickListener = listener;
            return this;
        }

        public InfoDialog create() {
            mBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mButtonClickListener.onClick(v);
                }
            });  //用户不可以点击外部来关闭 Dialog
            return infoDialog;
        }


        public Builder setBack() {
            mBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    infoDialog.dismiss();
                }
            });
            return this;
        }

    }


}
