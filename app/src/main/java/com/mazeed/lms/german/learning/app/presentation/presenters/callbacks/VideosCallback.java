package com.mazeed.lms.german.learning.app.presentation.presenters.callbacks;

import com.mazeed.lms.german.learning.app.domain.models.video.Video;

import java.util.List;

public interface VideosCallback extends BaseCallback {
    void onGetVideosComplete(List<Video> videos);
}
