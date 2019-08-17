
package com.mazeed.lms.german.learning.app.domain.models.contents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mazeed.lms.german.learning.app.domain.models.BaseModel;

import java.util.List;

public class Contents extends BaseModel {
    @SerializedName("Exam")
    private List<Content> exam;
    @SerializedName("Explain")
    private List<Content> explain;
    @SerializedName("GradeId")
    private Long gradeId;
    @SerializedName("Id")
    private Long id;
    @Expose
    private String name;
    @SerializedName("Situations")
    private List<Content> situations;
    @SerializedName("TextMessage")
    private List<Content> textMessage;
    @SerializedName("TopicsExpression")
    private List<Content> topicsExpression;
    @SerializedName("Video")
    private List<Content> video;
    @SerializedName("Word")
    private List<Content> word;

    public List<Content> getExam() {
        return exam;
    }

    public void setExam(List<Content> exam) {
        this.exam = exam;
    }

    public List<Content> getExplain() {
        return explain;
    }

    public void setExplain(List<Content> explain) {
        this.explain = explain;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Content> getSituations() {
        return situations;
    }

    public void setSituations(List<Content> situations) {
        this.situations = situations;
    }

    public List<Content> getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(List<Content> textMessage) {
        this.textMessage = textMessage;
    }

    public List<Content> getTopicsExpression() {
        return topicsExpression;
    }

    public void setTopicsExpression(List<Content> topicsExpression) {
        this.topicsExpression = topicsExpression;
    }

    public List<Content> getVideo() {
        return video;
    }

    public void setVideo(List<Content> video) {
        this.video = video;
    }

    public List<Content> getWord() {
        return word;
    }

    public void setWord(List<Content> word) {
        this.word = word;
    }
}
