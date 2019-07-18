package com.mazeed.lms.german.learning.app.domain.interactors.videos;

import com.mazeed.lms.german.learning.app.domain.interactors.MainInteractor;
import com.mazeed.lms.german.learning.app.domain.models.video.Video;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.models.MoreItem;

import java.util.List;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface VideosInteractor extends MainInteractor {
    void getVideos(VideosCallbackStates callback);

    interface VideosCallbackStates extends CallbackStates {
        void onGetVideosComplete(List<Video> videos);
    }
}
