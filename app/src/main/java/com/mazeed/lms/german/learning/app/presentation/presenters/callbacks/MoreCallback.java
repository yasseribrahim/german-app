package com.mazeed.lms.german.learning.app.presentation.presenters.callbacks;

import com.mazeed.lms.german.learning.app.presentation.ui.utils.models.MoreItem;

import java.util.List;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface MoreCallback extends BaseCallback {
    void onGetMoreItemsComplete(List<MoreItem> items);
}
