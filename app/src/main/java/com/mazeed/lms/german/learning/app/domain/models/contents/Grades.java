
package com.mazeed.lms.german.learning.app.domain.models.contents;

import com.google.gson.annotations.SerializedName;
import com.mazeed.lms.german.learning.app.domain.models.BaseModel;

import java.util.List;

public class Grades extends BaseModel {

    @SerializedName("Data")
    private List<Grade> grades;

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "grades=" + grades +
                "} " + super.toString();
    }
}
