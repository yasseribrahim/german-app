package com.mazeed.lms.german.learning.app.presentation.presenters.more;

import android.view.View;

import com.mazeed.lms.german.learning.app.domain.interactors.more.MoreInteractor;
import com.mazeed.lms.german.learning.app.domain.interactors.more.MoreInteractorImp;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.models.MoreItem;
import com.mazeed.lms.german.learning.app.presentation.presenters.callbacks.MoreCallback;

import java.util.List;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class MorePresenterImp implements MorePresenter, MoreInteractor.MoreCallbackStates {
    private MoreCallback view;
    private MoreInteractor interactor;

    public MorePresenterImp(MoreCallback view) {
        this.view = view;
        this.interactor = new MoreInteractorImp();
    }

    @Override
    public void getMoreItems() {
        interactor.getMoreItems(this);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        view = null;
        interactor.onDestroy();
    }

    @Override
    public void failure(String message, View.OnClickListener onClickListener) {
        if (view != null) {
            view.onShowError(message, onClickListener);
        }
    }

    @Override
    public void showProgress() {
        if (view != null) {
            view.onShowProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (view != null) {
            view.onHideProgress();
        }
    }

    @Override
    public void unAuthorized() {
        if (view != null) {
            view.onUnAuthorized();
        }
    }

    @Override
    public String getErrorMessage(Throwable throwable) {
        if (view != null) {
            view.onGetErrorMessage(throwable);
        }
        return null;
    }

    @Override
    public void onGetMoreItemsComplete(List<MoreItem> items) {
        if (view != null) {
            view.onGetMoreItemsComplete(items);
        }
    }
}
