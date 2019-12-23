package health.wd.com.health_query.core;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import health.wd.com.health_query.R;

/**
 * Time:  2019-12-17
 * Author:  杨世博
 * Description:
 */
public class CustomDialog extends Dialog {
    /**
     * 显示的图片
     */
    private ImageView imageIv ;

    /**
     * 显示的标题
     */


    /**
     * 显示的消息
     */
    private TextView pop_title ;

    /**
     * 确认和取消按钮
     */
    private Button pop_but ,pop_qu;

    /**
     * 按钮之间的分割线
     */
    private View columnLineView ;
    public CustomDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    /**
     * 都是内容数据
     */
    private String message;
    private String title;
    private String positive,negtive ;
    private int imageResId = -1 ;

    /**
     * 底部是否只有一个按钮
     */
    private boolean isSingle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_advisory);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        refreshView();
        //初始化界面控件的事件
        initEvent();
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        pop_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickBottomListener!= null) {
                    onClickBottomListener.onPositiveClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        pop_qu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickBottomListener!= null) {
                    onClickBottomListener.onNegtiveClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void refreshView() {
        //如果用户自定了title和message

        if (!TextUtils.isEmpty(message)) {
            pop_title.setText(message);
        }
        //如果设置按钮的文字
        if (!TextUtils.isEmpty(positive)) {
            pop_but.setText(positive);
        }else {
            pop_but.setText("去咨询");
        }
        if (!TextUtils.isEmpty(negtive)) {
            pop_qu.setText(negtive);
        }else {
            pop_qu.setText("取消");
        }

        if (imageResId!=-1){
            imageIv.setImageResource(imageResId);
            imageIv.setVisibility(View.VISIBLE);
        }else {
            imageIv.setVisibility(View.GONE);
        }
        /**
         * 只显示一个按钮的时候隐藏取消按钮，回掉只执行确定的事件
         */
//        if (isSingle){
//            columnLineView.setVisibility(View.GONE);
//            pop_qu.setVisibility(View.GONE);
//        }else {
//            pop_qu.setVisibility(View.VISIBLE);
//            columnLineView.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public void show() {
        super.show();
        refreshView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        pop_qu = (Button) findViewById(R.id.pop_qu);
        pop_but = (Button) findViewById(R.id.pop_but);
        pop_title = (TextView) findViewById(R.id.pop_title);
        imageIv = (ImageView) findViewById(R.id.pop_image);

    }

    /**
     * 设置确定取消按钮的回调
     */
    public OnClickBottomListener onClickBottomListener;
    public CustomDialog setOnClickBottomListener(OnClickBottomListener onClickBottomListener) {
        this.onClickBottomListener = onClickBottomListener;
        return this;
    }
    public interface OnClickBottomListener{
        /**
         * 点击确定按钮事件
         */
        public void onPositiveClick();
        /**
         * 点击取消按钮事件
         */
        public void onNegtiveClick();
    }

    public String getMessage() {
        return message;
    }

    public CustomDialog setMessage(String message) {
        this.message = message;
        return this ;
    }

    public String getTitle() {
        return title;
    }

    public CustomDialog setTitle(String title) {
        this.title = title;
        return this ;
    }

    public String getPositive() {
        return positive;
    }

    public CustomDialog setPositive(String positive) {
        this.positive = positive;
        return this ;
    }

    public String getNegtive() {
        return negtive;
    }

    public CustomDialog setNegtive(String negtive) {
        this.negtive = negtive;
        return this ;
    }

    public int getImageResId() {
        return imageResId;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public CustomDialog setSingle(boolean single) {
        isSingle = single;
        return this ;
    }

    public CustomDialog setImageResId(int imageResId) {
        this.imageResId = imageResId;
        return this ;
    }
}
