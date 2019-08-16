
package com.mazeed.lms.german.learning.app.domain.models.contents;

import com.google.gson.annotations.SerializedName;
import com.mazeed.lms.german.learning.app.domain.models.BaseModel;

import java.util.List;

public class Lessons extends BaseModel {

    @SerializedName("Data")
    private List<Lesson> lessons;

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Lessons{" +
                "lessons=" + lessons +
                "} " + super.toString();
    }
}
