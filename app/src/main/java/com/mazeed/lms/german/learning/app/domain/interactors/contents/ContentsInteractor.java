package com.mazeed.lms.german.learning.app.domain.interactors.contents;

import com.mazeed.lms.german.learning.app.domain.interactors.MainInteractor;
import com.mazeed.lms.german.learning.app.domain.models.contents.Grade;
import com.mazeed.lms.german.learning.app.domain.models.contents.Lesson;
import com.mazeed.lms.german.learning.app.domain.models.contents.LessonDetails;

import java.util.List;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface ContentsInteractor extends MainInteractor {
    void getAllGrades();

    void getLessons(int gradeId);

    void getLessonDetails(int lessonId);

    interface ContentsCallbackStates extends CallbackStates {
        void onGetGradesComplete(List<Grade> grades);

        void onGetLessonsComplete(List<Lesson> lessons);

        void onGetLessonDetailsComplete(LessonDetails details);
    }
}
