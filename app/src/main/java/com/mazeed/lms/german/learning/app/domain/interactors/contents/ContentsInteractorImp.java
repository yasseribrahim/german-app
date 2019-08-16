package com.mazeed.lms.german.learning.app.domain.interactors.contents;

import android.view.View;

import com.mazeed.lms.german.learning.app.domain.controller.Controller;
import com.mazeed.lms.german.learning.app.domain.interactors.BaseInteractor;
import com.mazeed.lms.german.learning.app.domain.models.contents.Grades;
import com.mazeed.lms.german.learning.app.domain.models.contents.LessonDetails;
import com.mazeed.lms.german.learning.app.domain.models.contents.Lessons;
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
    public void getAllGrades() {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.getAllGrades(user.getToken()), new GetGradesCompleteObserver(callback));
    }

    @Override
    public void getLessons(int gradeId) {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.getLessonsByGradeId(user.getToken(), gradeId), new GetLessonsCompleteObserver(gradeId, callback));
    }

    @Override
    public void getLessonDetails(int lessonId) {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.getLessonDetails(user.getToken(), lessonId), new GetLessonDetailsCompleteObserver(lessonId, callback));
    }

    private final class GetGradesCompleteObserver extends BaseObserver<Grades> {

        public GetGradesCompleteObserver(ContentsCallbackStates callback) {
            super(callback);
        }

        @Override
        public void onNext(Grades grades) {
            callback.onGetGradesComplete(grades.getGrades());
            super.onNext(grades);
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
                    getAllGrades();
                }
            });
        }
    }

    private final class GetLessonsCompleteObserver extends BaseObserver<Lessons> {
        private int gradeId;

        public GetLessonsCompleteObserver(int gradeId, ContentsCallbackStates callback) {
            super(callback);
            this.gradeId = gradeId;
        }

        @Override
        public void onNext(Lessons lessons) {
            callback.onGetLessonsComplete(lessons.getLessons());
            super.onNext(lessons);
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
                    getLessons(gradeId);
                }
            });
        }
    }

    private final class GetLessonDetailsCompleteObserver extends BaseObserver<LessonDetails> {
        private int lessonId;

        public GetLessonDetailsCompleteObserver(int lessonId, ContentsCallbackStates callback) {
            super(callback);
            this.lessonId = lessonId;
        }

        @Override
        public void onNext(LessonDetails details) {
            callback.onGetLessonDetailsComplete(details);
            super.onNext(details);
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
                    getLessonDetails(lessonId);
                }
            });
        }
    }
}
