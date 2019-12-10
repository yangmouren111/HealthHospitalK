package com.wd.health_main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wd.health_main.activity.WriteSickCircleActivity;
import com.wd.health_main.fragment.CircleFragment;
import com.wd.health_main.fragment.ShowFragment;
import com.wd.health_main.fragment.VideoFragment;
import com.wd.health_vedio.view.activity.VideoActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R2.id.rb_shou_wei)
    ImageView rbShouWei;
    @BindView(R2.id.rb_shou_xuan)
    ImageView rbShouXuan;
    @BindView(R2.id.home_lin)
    LinearLayout homeLin;
    @BindView(R2.id.rb_movie_wei)
    ImageView rbMovieWei;
    @BindView(R2.id.rb_movie_xuan)
    ImageView rbMovieXuan;
    @BindView(R2.id.movie_lin)
    LinearLayout movieLin;
    @BindView(R2.id.sick_circle_img)
    ImageView sickCircleImg;
    @BindView(R2.id.comments_img)
    ImageView commentsImg;
    @BindView(R2.id.sick_lin)
    LinearLayout sickLin;
    @BindView(R2.id.vp)
    FrameLayout vp;
    private ShowFragment showFragment;
    private CircleFragment circleFragment;

    private List<Fragment> list = new ArrayList<>();
    private long mExitTime;
    private VideoActivity videoActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }


        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        showFragment = new ShowFragment();
        circleFragment = new CircleFragment();
        videoActivity = new VideoActivity();
        list.add(showFragment);
        list.add(circleFragment);
        list.add(videoActivity);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.vp, showFragment)
                .add(R.id.vp, circleFragment)
                .add(R.id.vp, videoActivity)
                .show(showFragment)
                .hide(circleFragment)
                .hide(videoActivity)
                .commit();

    }


    @OnClick({R2.id.home_lin, R2.id.movie_lin, R2.id.sick_lin,R2.id.comments_img})
    public void onViewClicked(View view) {
        commentsImg.setVisibility(View.GONE);
        rbMovieXuan.setVisibility(View.GONE);
        rbShouXuan.setVisibility(View.GONE);

        sickCircleImg.setVisibility(View.VISIBLE);
        rbMovieWei.setVisibility(View.VISIBLE);
        rbShouWei.setVisibility(View.VISIBLE);
        int i = view.getId();
        if (i == R.id.home_lin) {
            rbShouXuan.setVisibility(View.VISIBLE);
            rbShouWei.setVisibility(View.GONE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(showFragment)
                    .hide(circleFragment)
                    .hide(videoActivity)
                    .commit();

        } else if (i == R.id.movie_lin) {

            rbMovieXuan.setVisibility(View.VISIBLE);
            rbMovieWei.setVisibility(View.GONE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .show(videoActivity)
                    .hide(circleFragment)
                    .hide(showFragment)
                    .commit();

        } else if (i == R.id.sick_lin) {
            commentsImg.setVisibility(View.VISIBLE);
            sickCircleImg.setVisibility(View.GONE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(circleFragment)
                    .hide(videoActivity)
                    .hide(showFragment)
                    .commit();

        } else if (i == R.id.comments_img) {
            Intent intent = new Intent(MainActivity.this,WriteSickCircleActivity.class);
            startActivity(intent);

        }
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
