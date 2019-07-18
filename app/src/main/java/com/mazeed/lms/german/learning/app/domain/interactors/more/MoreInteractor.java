package com.mazeed.lms.german.learning.app.domain.interactors.more;

import com.mazeed.lms.german.learning.app.domain.interactors.MainInteractor;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.models.MoreItem;

import java.util.List;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface MoreInteractor extends MainInteractor {
    void getMoreItems(MoreCallbackStates callback);

    interface MoreCallbackStates extends CallbackStates {
        void onGetMoreItemsComplete(List<MoreItem> items);
    }
}
