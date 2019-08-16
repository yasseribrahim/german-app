package com.mazeed.lms.german.learning.app.presentation.presenters.contents;

import com.mazeed.lms.german.learning.app.presentation.presenters.MainPresenter;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface ContentsPresenter extends MainPresenter {
    void getAllGrades();

    void getLessons(int gradeId);

    void getLessonDetails(int lessonId);
}
