
package com.mazeed.lms.german.learning.app.domain.models.contents;

import com.google.gson.annotations.SerializedName;
import com.mazeed.lms.german.learning.app.domain.models.BaseModel;

import java.util.List;

public class Groups extends BaseModel {

    @SerializedName("Data")
    private List<Group> groups;

    public Groups() {
        super();
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
