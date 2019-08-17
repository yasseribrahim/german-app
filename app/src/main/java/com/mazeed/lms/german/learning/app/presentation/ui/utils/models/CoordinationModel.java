package com.mazeed.lms.german.learning.app.presentation.ui.utils.models;

import java.util.List;

public class CoordinationModel<T> {
    private String title;
    private List<T> list;
    private int type;

    public CoordinationModel() {
    }

    public CoordinationModel(String title, List<T> list, int type) {
        this.title = title;
        this.list = list;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
