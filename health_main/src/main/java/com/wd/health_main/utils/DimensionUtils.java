package com.wd.health_main.utils;

import android.content.Context;

/**
 * Time:  2019-12-09
 * Author:  杨世博
 * Description:
 */
public class DimensionUtils {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale +0.5f);
    }

}
