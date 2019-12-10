// Generated code from Butter Knife. Do not modify!
package com.wd.health_vedio.view.activity;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kd.easybarrage.BarrageView;
import com.wd.health_vedio.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoActivity_ViewBinding implements Unbinder {
  private VideoActivity target;

  @UiThread
  public VideoActivity_ViewBinding(VideoActivity target, View source) {
    this.target = target;

    target.mGroup = Utils.findRequiredViewAsType(source, R.id.video_group_recycler, "field 'mGroup'", RecyclerView.class);
    target.dropDown = Utils.findRequiredViewAsType(source, R.id.video_drop_down, "field 'dropDown'", ImageView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.video_recycler, "field 'mRecyclerView'", RecyclerView.class);
    target.mDanmu = Utils.findRequiredViewAsType(source, R.id.video_danmu, "field 'mDanmu'", BarrageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VideoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mGroup = null;
    target.dropDown = null;
    target.mRecyclerView = null;
    target.mDanmu = null;
  }
}
