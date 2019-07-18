package com.mazeed.lms.german.learning.app.presentation.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.halilibo.bvpkotlin.BetterVideoPlayer;
import com.halilibo.bvpkotlin.VideoCallback;
import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.models.video.Video;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoPlayerActivity extends BaseActivity implements VideoCallback {

    @BindView(R.id.containerView)
    RelativeLayout containerView;
    @BindView(R.id.player)
    BetterVideoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        ButterKnife.bind(this);

        Video video = getIntent().getParcelableExtra(Constants.KEY_VIDEO);

        player.showControls();
        player.toggleControls();
        player.setCallback(this);
        player.setSource(Uri.parse(video.getUrl()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.pause();
        }
    }

    @Override
    protected View getSnackBarAnchorView() {
        return containerView;
    }

    @Override
    public void onBuffering(int i) {
        Log.i(VideoPlayerActivity.class.getSimpleName(), "onBuffering: " + i);
    }

    @Override
    public void onCompletion(BetterVideoPlayer betterVideoPlayer) {
        Log.i(VideoPlayerActivity.class.getSimpleName(), "onCompletion: " + betterVideoPlayer);
    }

    @Override
    public void onError(BetterVideoPlayer betterVideoPlayer, Exception e) {
        Log.i(VideoPlayerActivity.class.getSimpleName(), "onError: " + e);
    }

    @Override
    public void onPaused(BetterVideoPlayer betterVideoPlayer) {
        Log.i(VideoPlayerActivity.class.getSimpleName(), "onPaused: " + betterVideoPlayer);
    }

    @Override
    public void onPrepared(BetterVideoPlayer betterVideoPlayer) {
        Log.i(VideoPlayerActivity.class.getSimpleName(), "onPrepared: " + betterVideoPlayer);
    }

    @Override
    public void onPreparing(BetterVideoPlayer betterVideoPlayer) {
        Log.i(VideoPlayerActivity.class.getSimpleName(), "onPreparing: " + betterVideoPlayer);
    }

    @Override
    public void onStarted(BetterVideoPlayer betterVideoPlayer) {
        Log.i(VideoPlayerActivity.class.getSimpleName(), "onStarted: " + betterVideoPlayer);
    }

    @Override
    public void onToggleControls(BetterVideoPlayer betterVideoPlayer, boolean b) {
        Log.i(VideoPlayerActivity.class.getSimpleName(), "onToggleControls: " + b);
    }
}
