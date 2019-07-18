package com.mazeed.lms.german.learning.app.domain.interactors.videos;

import android.view.View;

import com.mazeed.lms.german.learning.app.domain.interactors.BaseInteractor;
import com.mazeed.lms.german.learning.app.domain.models.video.Video;
import com.mazeed.lms.german.learning.app.domain.utils.DataSet;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class VideosInteractorImp extends BaseInteractor implements VideosInteractor {
    public VideosInteractorImp() {
    }

    @Override
    public void getVideos(final VideosCallbackStates callback) {
        Observable<List<Video>> observable = Observable.create(new ObservableOnSubscribe<List<Video>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Video>> emitter) throws Exception {
                callback.showProgress();
                emitter.onNext(DataSet.getData());
            }
        });
        prepare(observable, new BaseObserver<List<Video>>(callback) {
            @Override
            public void onNext(List<Video> videos) {
                super.onNext(videos);
                callback.onGetVideosComplete(videos);
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                callback.failure(e.getMessage(), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getVideos(callback);
                    }
                });
            }
        });
    }
}
