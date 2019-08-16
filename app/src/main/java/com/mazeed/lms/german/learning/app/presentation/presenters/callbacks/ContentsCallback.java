package com.mazeed.lms.german.learning.app.presentation.presenters.callbacks;

import com.mazeed.lms.german.learning.app.domain.models.contents.Grade;
import com.mazeed.lms.german.learning.app.domain.models.contents.Lesson;
import com.mazeed.lms.german.learning.app.domain.models.contents.LessonDetails;

import java.util.List;

public interface ContentsCallback extends BaseCallback {

    default void onGetGradesComplete(List<Grade> grades) {
    }

    default void onGetLessonsComplete(List<Lesson> lessons) {
    }

    default void onGetLessonDetailsComplete(LessonDetails details) {
    }
}
