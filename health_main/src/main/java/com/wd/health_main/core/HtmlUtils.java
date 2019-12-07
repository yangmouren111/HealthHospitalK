package com.wd.health_main.core;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.Build;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Time:  2019-12-06
 * Author:  杨世博
 * Description:
 */
public class HtmlUtils {
    TextView mTextView;

    public HtmlUtils(TextView textView) {
        this.mTextView = textView;
    }

//    @Override
//    public Drawable getDrawable(String source) {
//        String sources="";
//        final URLDrawable urlDrawable = new URLDrawable();
//
//        ImageLoader.getInstance().loadImage(sources, new SimpleImageLoadingListener() {
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                urlDrawable.bitmap= loadedImage;
//                urlDrawable.setBounds(0, 0, loadedImage.getWidth(), loadedImage.getHeight());
//                mTextView.invalidate();
//                mTextView.setText(mTextView.getText()); // 解决图文重叠
//            }
//        });
//        return urlDrawable;
//    }



}
