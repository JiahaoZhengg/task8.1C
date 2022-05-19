package com.game.mp4.activity;

import android.net.Uri;

import com.game.mp4.BaseBindingActivity;
import com.game.mp4.databinding.ActivityPlayBinding;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;


public class PlayActivity extends BaseBindingActivity<ActivityPlayBinding> {

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        initializePlayer();

    }

    ExoPlayer player;

    private void initializePlayer() {
        //全部使用默认设置初始化ExoPlayer
        player = new ExoPlayer.Builder(this).build();
        //将显示控件绑定ExoPlayer
        viewBinder.playView.setPlayer(player);
        String path = getIntent().getStringExtra("path");
//        String title = getIntent().getStringExtra("title");
//        viewBinder.title.setText(title);
        Uri parse = Uri.parse(path);
        MediaItem item = MediaItem.fromUri(parse);

        //设置ExoPlayer需要播放的多媒体item
        player.setMediaItem(item);
        //设置播放器是否当装备好就播放， 如果看源码可以看出，ExoPlayer的play()方法也是调用的这个方法
        player.setPlayWhenReady(true);
        //初始化播放位置
        player.seekTo(0, 0);
        //资源准备，如果设置 setPlayWhenReady(true) 则资源准备好就立马播放。
        player.prepare();
        player.play();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }
}