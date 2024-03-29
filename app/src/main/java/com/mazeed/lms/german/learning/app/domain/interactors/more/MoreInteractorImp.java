package com.mazeed.lms.german.learning.app.domain.interactors.more;

import android.view.View;

import com.mazeed.lms.german.learning.app.domain.interactors.BaseInteractor;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.models.MoreItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class MoreInteractorImp extends BaseInteractor implements MoreInteractor {
    public MoreInteractorImp() {
    }

    @Override
    public void getMoreItems(final MoreCallbackStates callback) {
        Observable<List<MoreItem>> observable = Observable.create(new ObservableOnSubscribe<List<MoreItem>>() {
            @Override
            public void subscribe(ObservableEmitter<List<MoreItem>> emitter) throws Exception {
                callback.showProgress();
                List<MoreItem> items = new ArrayList<>();
                emitter.onNext(items);
            }
        });
        prepare(observable, new BaseObserver<List<MoreItem>>(callback) {
            @Override
            public void onNext(List<MoreItem> items) {
                super.onNext(items);
                callback.onGetMoreItemsComplete(items);
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
                        getMoreItems(callback);
                    }
                });
            }
        });
    }
}
