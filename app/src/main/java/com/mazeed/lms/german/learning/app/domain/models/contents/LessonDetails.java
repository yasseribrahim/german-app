
package com.mazeed.lms.german.learning.app.domain.models.contents;

import com.google.gson.annotations.SerializedName;
import com.mazeed.lms.german.learning.app.domain.models.BaseModel;

public class LessonDetails extends BaseModel {

    @SerializedName("Data")
    private Contents contents;

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public Contents getContents() {
        return contents;
    }
}
