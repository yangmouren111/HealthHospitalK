package com.wd.health_vedio.view.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.opengl.ETC1;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.kd.easybarrage.Barrage;
import com.kd.easybarrage.BarrageView;
import com.wd.common.bean.VideoComment;
import com.wd.common.bean.VideoGroup;
import com.wd.common.bean.VideoVo;
import com.wd.common.core.DataCall;
import com.wd.common.core.WDActivity;
import com.wd.common.core.WDFragment;
import com.wd.common.core.exception.ApiException;
import com.wd.health_vedio.R;
import com.wd.health_vedio.R2;
import com.wd.health_vedio.adapter.VideoGroupAdapter;
import com.wd.health_vedio.presenter.AddUserVideoPresenter;
import com.wd.health_vedio.presenter.AddVideoCommentPresenter;
import com.wd.health_vedio.presenter.FindVideoCategoryPresenter;
import com.wd.health_vedio.presenter.FindVideoCommentPresenter;
import com.wd.health_vedio.presenter.FindVideoVoPresenter;
import com.wd.health_vedio.adapter.ViewPagerLayoutManagerAdapter;
import com.wd.health_vedio.presenter.VideoBuyPresenter;
import com.wd.health_vedio.view.iview.InfoDialog;
import com.wd.health_vedio.view.viewpager.OnViewPagerListener;
import com.wd.health_vedio.view.viewpager.ViewPagerLayoutManager;

import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

import static com.wd.common.core.WDApplication.getContext;

public class VideoActivity extends WDFragment {


    @BindView(R2.id.video_group_recycler)
    RecyclerView mGroup;
    @BindView(R2.id.video_drop_down)
    ImageView dropDown;
    private static final String TAG = "ViewPagerActivity";
    @BindView(R2.id.video_recycler)
    RecyclerView mRecyclerView;
    @BindView(R2.id.video_danmu)
    BarrageView mDanmu;
    private ViewPagerLayoutManagerAdapter mVideoAdapter;
    private ViewPagerLayoutManager mLayoutManager;

    private FindVideoVoPresenter findVideoVoPresenter;
    private FindVideoCategoryPresenter findVideoCategoryPresenter;
    private VideoGroupAdapter videoGroupAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {

                ObjectAnimator imageX = new ObjectAnimator().ofFloat(dropDown, "translationX", 0, 0);
                ObjectAnimator imageY = new ObjectAnimator().ofFloat(dropDown, "translationY", -250f, 0);
                //组合动画
                AnimatorSet imageSer = new AnimatorSet();
                imageSer.playTogether(imageX, imageY); //设置动画
                imageSer.setDuration(1000);  //设置动画时间
                imageSer.start();


                ObjectAnimator groupX = new ObjectAnimator().ofFloat(mGroup, "translationX", 0, 0);
                ObjectAnimator groupY = new ObjectAnimator().ofFloat(mGroup, "translationY", 200f, 0);
                //组合动画
                AnimatorSet groupSet = new AnimatorSet();
                groupSet.playTogether(groupX, groupY); //设置动画
                groupSet.setDuration(1000);  //设置动画时间
                groupSet.start();
            }
        }
    };
    private VideoBuyPresenter videoBuyPresenter;
    private AlertDialog alertDialog;
    private InfoDialog dialog;
    private AddVideoCommentPresenter addVideoCommentPresenter;
    private FindVideoCommentPresenter findVideoCommentPresenter;
    private AddUserVideoPresenter addUserVideoPresenter;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        //上标题
        mGroup.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        videoGroupAdapter = new VideoGroupAdapter();
        mGroup.setAdapter(videoGroupAdapter);
        //视频
        findVideoCategoryPresenter = new FindVideoCategoryPresenter(new FindVideoCategoryBack());
        findVideoCategoryPresenter.reqeust();

        mLayoutManager = new ViewPagerLayoutManager(getContext(), OrientationHelper.VERTICAL);
        mVideoAdapter = new ViewPagerLayoutManagerAdapter();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mVideoAdapter);

        findVideoVoPresenter = new FindVideoVoPresenter(new FindVideoVoBack());
        videoBuyPresenter = new VideoBuyPresenter(new VideoBuyBack());
        addVideoCommentPresenter = new AddVideoCommentPresenter(new AddVideoCommentBack());
        findVideoCommentPresenter = new FindVideoCommentPresenter(new FindCommentBack());
        addUserVideoPresenter = new AddUserVideoPresenter(new AddUserVideo());

        initListener();
    }

    class AddUserVideo implements DataCall{

        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
            mVideoAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(getContext(), "收藏失败", Toast.LENGTH_SHORT).show();
        }
    }

    //查询视频评论列表
    class FindCommentBack implements DataCall<List<VideoComment>>{
        @Override
        public void success(final List<VideoComment> data, Object... args) {
            for (int i = 0; i < data.size(); i++) {
                mDanmu.addBarrage(new Barrage(data.get(i).content, R.color.colorWrite));
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    //发表视频评论（弹幕）
    class AddVideoCommentBack implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(getContext(), "发布成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(getContext(), "发布失败", Toast.LENGTH_SHORT).show();
        }
    }

    //健康课堂视频购买
    class VideoBuyBack implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(getContext(), "购买成功", Toast.LENGTH_SHORT).show();
            mVideoAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(getContext(), "购买失败", Toast.LENGTH_SHORT).show();
        }
    }

    //根据视频类目查询视频列表
    class FindVideoCategoryBack implements DataCall<List<VideoGroup>> {
        @Override
        public void success(final List<VideoGroup> data, Object... args) {
            videoGroupAdapter.addAll(data);
            videoGroupAdapter.notifyDataSetChanged();

            findVideoVoPresenter.reqeust(1, "1", data.get(0).id);

            videoGroupAdapter.setVideoGroupItemOnClickListener(new VideoGroupAdapter.VideoGroupItemOnClickListener() {
                @Override
                public void onClick(View view, int id) {
                    mVideoAdapter.clear();
                    findVideoVoPresenter.reqeust(1, "1", id);
                    handler.removeCallbacksAndMessages(null);
                    Message message = new Message();
                    message.what = 0;
                    handler.sendMessageDelayed(message, 5000);
                }
            });
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //查询健康讲堂类目
    class FindVideoVoBack implements DataCall<List<VideoVo>> {
        @Override
        public void success(List<VideoVo> data, Object... args) {
            Log.i("success: ", data.get(0).originalUrl);
            mVideoAdapter.addAll(data);
            mVideoAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }


    private void initListener() {
        mVideoAdapter.setSetUserVideo(new ViewPagerLayoutManagerAdapter.SetUserVideo() {
            @Override
            public void onUser(int videoId) {
                addUserVideoPresenter.reqeust(1,"1",videoId);
            }
        });

        mVideoAdapter.setDanMuHide(new ViewPagerLayoutManagerAdapter.DanMuHide() {
            @Override
            public void onDanMuHide(Boolean isHide) {
                if (isHide){
                    mDanmu.setVisibility(View.VISIBLE);
                }else {
                    mDanmu.setVisibility(View.GONE);
                }
            }
        });

        mVideoAdapter.setFindVideoComment(new ViewPagerLayoutManagerAdapter.FindVideoComment() {
            @Override
            public void findComment(int videoId) {
                findVideoCommentPresenter.reqeust(videoId);
            }
        });

        mVideoAdapter.setOnVideoBuy(new ViewPagerLayoutManagerAdapter.OnVideoBuy() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void videoBuy(final int videoId, final int price) {
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.buy_video_window, null);
                        final PopupWindow popWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        popWindow.setOutsideTouchable(true);      //必须设置背景
                        popWindow.setBackgroundDrawable(null);
                        popWindow.setFocusable(true);
                        popWindow.setAnimationStyle(R.style.AnimationPreview);
                        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
                        popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                        TextView mPrice = view.findViewById(R.id.video_window_videoPrice);
                        mPrice.setText(price+"H币");
                        view.findViewById(R.id.video_window_back).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popWindow.dismiss();
                            }
                        });
                        view.findViewById(R.id.video_window_buyBtn).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                videoBuyPresenter.reqeust(1,"1",videoId,price);
                            }
                        });
                    }
                };
                dialog = new InfoDialog.Builder(getContext())
                        .setTitle("购买本视频将扣除"+price+"H币！")
                        .setBack()
                        .setButton(onClickListener)
                        .create();
                dialog.show();
            }
        });

        mVideoAdapter.setOnAddComment(new ViewPagerLayoutManagerAdapter.OnAddComment() {
            @Override
            public void addComment(final int videoId) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.video_secetr_text, null);
                final PopupWindow popWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popWindow.setOutsideTouchable(true);      //必须设置背景
                popWindow.setBackgroundDrawable(null);
                popWindow.setFocusable(true);
                //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
                popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                final EditText edd = view.findViewById(R.id.video_setComment);
                view.findViewById(R.id.video_seedComment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!edd.getText().toString().equals("")){
                            mDanmu.addBarrage(new Barrage(String.valueOf(edd.getText()), R.color.colorAccent));
                            addVideoCommentPresenter.reqeust(1,"1",videoId,String.valueOf(edd.getText()));
                            popWindow.dismiss();
                        }else {
                            popWindow.dismiss();
                        }
                    }
                });

                //直接显示在参照View 的左下方
                //popWindow.showAsDropDown(View anchor);
                //可以通过xoff，yOff,来调节x,y方向的偏移
                //popWindow.showAsDropDown(View anchor, int xoff, int off)
                //相对于整个屏幕的window而言，通过gravity调整显示在左、上、右、下、中. x,y调整两个方向的偏移
                //popWindow.showAsDropDown(View parent, int gravity, int x, int y)

            }
        });


        dropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator imageX = new ObjectAnimator().ofFloat(dropDown, "translationX", 0, 0);
                ObjectAnimator imageY = new ObjectAnimator().ofFloat(dropDown, "translationY", 0, -250f);
                //组合动画
                AnimatorSet imageSer = new AnimatorSet();
                imageSer.playTogether(imageX, imageY); //设置动画
                imageSer.setDuration(1000);  //设置动画时间
                imageSer.start();


                ObjectAnimator groupX = new ObjectAnimator().ofFloat(mGroup, "translationX", 0, 0);
                ObjectAnimator groupY = new ObjectAnimator().ofFloat(mGroup, "translationY", 0, 200f);
                //组合动画
                AnimatorSet groupSet = new AnimatorSet();
                groupSet.playTogether(groupX, groupY); //设置动画
                groupSet.setDuration(1000);  //设置动画时间
                groupSet.start();

                Message message = new Message();
                message.what = 0;
                handler.sendMessageDelayed(message, 5000);
            }
        });

        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                Log.e(TAG, "onInitComplete");
                playVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                Log.e(TAG, "释放位置:" + position + " 下一页:" + isNext);
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                Log.e(TAG, "选中位置:" + position + "  是否是滑动到底部:" + isBottom);
                playVideo(position);
            }
        });

    }

    private void playVideo(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final RelativeLayout rootView = itemView.findViewById(R.id.root_view);
        final SeekBar seekBar = itemView.findViewById(R.id.video_mySeekBar);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];

        videoView.start();


        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });


        imgPlay.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;

            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    imgPlay.animate().alpha(1f).start();
                    videoView.pause();
                    isPlaying = false;
                } else {
                    imgPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    //将长度转换为时间
    StringBuilder mFormatBuilder = new StringBuilder();
    Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

    //将长度转换为时间
    private String stringForTime(int timeMs) {
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }


    private void releaseVideo(int index) {
        View itemView = mRecyclerView.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();
        imgThumb.animate().alpha(1).start();
        imgPlay.animate().alpha(0f).start();

    }


    protected void destoryData() {
        handler.removeCallbacksAndMessages(null);
        mDanmu.destroy();
    }


}
