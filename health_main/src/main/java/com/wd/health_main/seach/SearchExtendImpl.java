package com.wd.health_main.seach;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Time:  2019-12-09
 * Author:  杨世博
 * Description:
 */
public interface SearchExtendImpl {
    int getLayoutId();

    ImageView getImageView();

    EditText getEditText();

    ViewGroup getSearchFrame();

    void initChildView();
}
