package com.mazeed.lms.german.learning.app.domain.interactors.contents;

import com.mazeed.lms.german.learning.app.domain.interactors.MainInteractor;
import com.mazeed.lms.german.learning.app.domain.models.contents.Content;
import com.mazeed.lms.german.learning.app.domain.models.contents.Group;

import java.util.List;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface ContentsInteractor extends MainInteractor {
    void getContents();

    void getContentsByGroupId(int groupId);

    void getGroups();

    interface ContentsCallbackStates extends CallbackStates {
        void onGetContentsComplete(List<Content> contents);

        void onGetGroupsComplete(List<Group> groups);
    }
}
