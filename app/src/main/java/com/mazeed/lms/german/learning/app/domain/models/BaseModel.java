
package com.mazeed.lms.german.learning.app.domain.models;

import com.google.gson.annotations.SerializedName;

public class BaseModel {
    @SerializedName("Msg")
    private String msg;
    @SerializedName("State")
    private Long state;

    public BaseModel() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

}
