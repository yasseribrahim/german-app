
package com.mazeed.lms.german.learning.app.domain.models.contents;

import com.google.gson.annotations.SerializedName;
import com.mazeed.lms.german.learning.app.domain.models.BaseModel;

import java.util.ArrayList;

public class LessonDetails extends BaseModel {

    @SerializedName("Data")
    private Contents contents;

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public Contents getContents() {
        return contents;
    }

    public boolean isEmpty() {
        return (contents.getExam() == null || contents.getExam().isEmpty()) &&
                (contents.getExplain() == null || contents.getExplain().isEmpty()) &&
                (contents.getSituations() == null || contents.getSituations().isEmpty()) &&
                (contents.getTextMessage() == null || contents.getTextMessage().isEmpty()) &&
                (contents.getTopicsExpression() == null || contents.getTopicsExpression().isEmpty()) &&
                (contents.getVideo() == null || contents.getVideo().isEmpty()) &&
                (contents.getWord() != null || contents.getWord().isEmpty());
    }

    public void prepare() {
        if (contents.getExam() == null) {
            contents.setExam(new ArrayList<>());
        }
        if (contents.getExplain() == null) {
            contents.setExplain(new ArrayList<>());
        }
        if (contents.getSituations() == null) {
            contents.setSituations(new ArrayList<>());
        }
        if (contents.getTextMessage() == null) {
            contents.setTextMessage(new ArrayList<>());
        }
        if (contents.getTopicsExpression() == null) {
            contents.setTopicsExpression(new ArrayList<>());
        }
        if (contents.getVideo() == null) {
            contents.setVideo(new ArrayList<>());
        }
        if (contents.getWord() == null) {
            contents.setWord(new ArrayList<>());
        }
    }
}
