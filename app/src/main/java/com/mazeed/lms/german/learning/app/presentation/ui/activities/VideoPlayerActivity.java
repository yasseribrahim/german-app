package com.mazeed.lms.german.learning.app.presentation.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.models.contents.Content;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JzvdStd;

public class VideoPlayerActivity extends BaseActivity {

    @BindView(R.id.containerView)
    RelativeLayout containerView;
    @BindView(R.id.player)
    JzvdStd player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        ButterKnife.bind(this);

        Content content = getIntent().getParcelableExtra(Constants.KEY_CONTENT);
        player.setUp(content.getPath(), content.getGradeName());
    }

    @Override
    public void onBackPressed() {
        if (player.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.releaseAllVideos();
    }

    @Override
    protected View getSnackBarAnchorView() {
        return containerView;
    }
}
