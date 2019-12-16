package com.wd.health_vedio.adapter;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.common.bean.VideoVo;
import com.wd.health_vedio.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * ViewPagerLayoutManager
 */
public class ViewPagerLayoutManagerAdapter extends RecyclerView.Adapter<ViewPagerLayoutManagerAdapter.VideoVoViewHolder> {

    List<VideoVo> list = new ArrayList<>();

    public void addAll(List<VideoVo> videoVos) {
        list.addAll(videoVos);
    }

    public void clear() {
        list.clear();
    }

    @NonNull
    @Override
    public VideoVoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager, parent, false);
        return new VideoVoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VideoVoViewHolder holder, int position) {
        //id	int	健康视频id
        //categoryId	int	健康视频类目id
        //title	string	标题
        //shearUrl	string	剪切视频url(试看)
        //abstracts	string	摘要
        //originalUrl	string	原始视频url
        //price	int	价格（H币）
        //duration	int	时长（单位/秒）
        //whetherCollection	int	是否收藏
        //whetherBuy	int	是否购买 1= 是2 =否
        //buyNum	int	购买数
        final VideoVo videoVo = list.get(position);
        holder.mComment.setText(videoVo.abstracts);
        holder.mName.setText(videoVo.title);
        if (videoVo.buyNum<10000){
            holder.buyNum.setText(videoVo.buyNum+"人\n"+"已购买");

        }else if(videoVo.buyNum>=10000){
            // 具体的注册资本等信息（单位元）
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(videoVo.buyNum));
            // 转换为万元（除以10000）
            BigDecimal decimal = bigDecimal.divide(new BigDecimal("10000"));
            // 保留两位小数
            DecimalFormat formater = new DecimalFormat("0.0");
            // 四舍五入
            formater.setRoundingMode(RoundingMode.HALF_UP);
            // 格式化完成之后得出结果
            String formatNum = formater.format(decimal);
            holder.buyNum.setText(formatNum+"万人\n"+"已购买");
        }

        if (videoVo.whetherCollection == 2){
            holder.mUser.setVisibility(View.VISIBLE);
            holder.mNull.setVisibility(View.GONE);
            holder.mUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setUserVideo.onUser(videoVo.id);
                }
            });
        }else {
            holder.mUser.setVisibility(View.GONE);
            holder.mNull.setVisibility(View.VISIBLE);
        }

        if (videoVo.whetherBuy == 2){
            holder.videoView.setVideoURI(Uri.parse(videoVo.shearUrl));
            holder.videoBuy.setImageResource(R.mipmap.common_icon_toll_n);
            holder.videoBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onVideoBuy.videoBuy(videoVo.id,videoVo.price);
                }
            });
            if (setTryLook!=null)
                setTryLook.isTry(15);

        }else {
            holder.tryView.setVisibility(View.GONE);
            holder.videoView.setVideoURI(Uri.parse(videoVo.originalUrl));
            holder.videoBuy.setImageResource(R.mipmap.common_icon_comment_large_n);
            holder.videoBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAddComment.addComment(videoVo.id);
                }
            });
        }

        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            int currentPosition, duration;
            @Override
            public void onPrepared(MediaPlayer mp) {
                // 获得当前播放时间和当前视频的长度
                currentPosition = holder.videoView.getCurrentPosition();
                duration = holder.videoView.getDuration();
                int time = ((currentPosition * 100) / duration);
                // 设置进度条的主要进度，表示当前的播放时间
                holder.seekBar.setProgress(time);
                findVideoComment.findComment(videoVo.id);

                holder.mHide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.mHide.setVisibility(View.GONE);
                        holder.mGone.setVisibility(View.VISIBLE);
                        danMuHide.onDanMuHide(false);
                    }
                });

                holder.mGone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.mHide.setVisibility(View.VISIBLE);
                        holder.mGone.setVisibility(View.GONE);
                        danMuHide.onDanMuHide(true);
                    }
                });

                final Handler hand = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (msg.what==0){
                            // 获得当前播放时间和当前视频的长度
                            currentPosition = holder.videoView.getCurrentPosition();
                            duration = holder.videoView.getDuration();
                            int time = ((currentPosition * 100) / duration);
                            // 设置进度条的主要进度，表示当前的播放时间
                            holder.seekBar.setProgress(time);
                            Message mess = new Message();
                            mess.what =0;
                            sendMessageDelayed(mess,1000);
                        }
                    }
                };
                mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {

                        Message mess = new Message();
                        mess.what =0;
                        hand.sendMessageDelayed(mess,500);

                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VideoVoViewHolder extends RecyclerView.ViewHolder {
        ImageView img_thumb,videoBuy;
        VideoView videoView;
        ImageView img_play,mHide,mGone,mUser,mNull;
        RelativeLayout rootView;
        SeekBar seekBar;
        LinearLayout tryView;
        TextView mComment,mName,buyNum,tryLook;

        public VideoVoViewHolder(View itemView) {
            super(itemView);
            img_thumb = itemView.findViewById(R.id.img_thumb);
            videoBuy = itemView.findViewById(R.id.video_buy);
            videoView = itemView.findViewById(R.id.video_view);
            img_play = itemView.findViewById(R.id.img_play);
            rootView = itemView.findViewById(R.id.root_view);
            seekBar = itemView.findViewById(R.id.video_mySeekBar);
            mComment = itemView.findViewById(R.id.video_videoConnet);
            mName = itemView.findViewById(R.id.video_videoName);
            mHide = itemView.findViewById(R.id.video_danmu_hide);
            mGone = itemView.findViewById(R.id.video_danmu_gone);
            mUser = itemView.findViewById(R.id.video_addUserVideo);
            mNull = itemView.findViewById(R.id.video_removeUserVideo);
            buyNum = itemView.findViewById(R.id.video_buyNum);
            tryLook = itemView.findViewById(R.id.video_tryLook);
            tryView = itemView.findViewById(R.id.video_tryView);
        }
    }
    private OnVideoBuy onVideoBuy;

    public void setOnVideoBuy(OnVideoBuy onVideoBuy) {
        this.onVideoBuy = onVideoBuy;
    }

    public interface OnVideoBuy{
        void videoBuy(int videoId,int price);
    }

    private OnAddComment onAddComment;

    public void setOnAddComment(OnAddComment onAddComment) {
        this.onAddComment = onAddComment;
    }

    public interface OnAddComment{
        void addComment(int videoId);
    }

    private FindVideoComment findVideoComment;

    public void setFindVideoComment(FindVideoComment findVideoComment) {
        this.findVideoComment = findVideoComment;
    }

    public interface FindVideoComment{
        void findComment(int videoId);
    }

    private DanMuHide danMuHide;

    public void setDanMuHide(DanMuHide danMuHide) {
        this.danMuHide = danMuHide;
    }

    public interface DanMuHide{
        void onDanMuHide(Boolean isHide);
    }

    private SetUserVideo setUserVideo;

    public void setSetUserVideo(SetUserVideo setUserVideo) {
        this.setUserVideo = setUserVideo;
    }

    public interface SetUserVideo{
        void onUser(int videoId);
    }

    private SetTryLook setTryLook;

    public void setSetTryLook(SetTryLook setTryLook) {
        this.setTryLook = setTryLook;
    }

    public interface SetTryLook{
        void isTry(int miss);
    }



}
