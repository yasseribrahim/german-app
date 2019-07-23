package com.mazeed.lms.german.learning.app.presentation.presenters.callbacks;

import com.mazeed.lms.german.learning.app.domain.models.contents.Content;
import com.mazeed.lms.german.learning.app.domain.models.contents.Group;

import java.util.List;

public interface ContentsCallback extends BaseCallback {
    default void onGetContentsComplete(List<Content> contents) {
    }

    default void onGetGroupsComplete(List<Group> groups) {
    }
}
