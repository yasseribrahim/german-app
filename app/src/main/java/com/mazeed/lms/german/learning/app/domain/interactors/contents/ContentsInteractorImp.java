package com.mazeed.lms.german.learning.app.domain.interactors.contents;

import android.view.View;

import com.mazeed.lms.german.learning.app.domain.controller.Controller;
import com.mazeed.lms.german.learning.app.domain.interactors.BaseInteractor;
import com.mazeed.lms.german.learning.app.domain.models.contents.Contents;
import com.mazeed.lms.german.learning.app.domain.models.contents.Groups;
import com.mazeed.lms.german.learning.app.domain.models.user.User;
import com.mazeed.lms.german.learning.app.domain.utils.UserManager;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.HttpException;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class ContentsInteractorImp extends BaseInteractor implements ContentsInteractor {
    private final Controller.ContentsController controller;
    private final ContentsCallbackStates callback;

    public ContentsInteractorImp(Controller.ContentsController controller, ContentsCallbackStates callback) {
        this.controller = controller;
        this.callback = callback;
    }

    @Override
    public void getContents() {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.getAll(user.getToken()), new GetContentsCompleteObserver(callback));
    }

    @Override
    public void getContentsByGroupId(int groupId) {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.getAllByGroupId(user.getToken(), groupId), new GetContentsCompleteObserver(callback));
    }

    @Override
    public void getGroups() {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.getAllGroups(user.getToken()), new GetGroupsCompleteObserver(callback));
    }

    private final class GetContentsCompleteObserver extends BaseObserver<Contents> {

        public GetContentsCompleteObserver(ContentsCallbackStates callback) {
            super(callback);
        }

        @Override
        public void onNext(Contents contents) {
            callback.onGetContentsComplete(contents.getContents());
            super.onNext(contents);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            if (e instanceof HttpException) {
                if (((HttpException) e).code() == HttpsURLConnection.HTTP_UNAUTHORIZED || ((HttpException) e).code() == HttpsURLConnection.HTTP_BAD_REQUEST) {
                    callback.unAuthorized();
                    return;
                }
            }
            String message = callback.getErrorMessage(e);
            callback.failure(message != null ? message : e.getMessage(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getContents();
                }
            });
        }
    }

    private final class GetGroupsCompleteObserver extends BaseObserver<Groups> {

        public GetGroupsCompleteObserver(ContentsCallbackStates callback) {
            super(callback);
        }

        @Override
        public void onNext(Groups groups) {
            callback.onGetGroupsComplete(groups.getGroups());
            super.onNext(groups);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            if (e instanceof HttpException) {
                if (((HttpException) e).code() == HttpsURLConnection.HTTP_UNAUTHORIZED || ((HttpException) e).code() == HttpsURLConnection.HTTP_BAD_REQUEST) {
                    callback.unAuthorized();
                    return;
                }
            }
            String message = callback.getErrorMessage(e);
            callback.failure(message != null ? message : e.getMessage(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getContents();
                }
            });
        }
    }
}
