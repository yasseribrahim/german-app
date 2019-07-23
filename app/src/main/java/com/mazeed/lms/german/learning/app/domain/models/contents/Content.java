
package com.mazeed.lms.german.learning.app.domain.models.contents;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content implements Parcelable {
    @SerializedName("GradeId")
    private Long gradeId;
    @SerializedName("GradeName")
    private String gradeName;
    @SerializedName("GroupId")
    private Long groupId;
    @SerializedName("GroupName")
    private String groupName;
    @SerializedName("Id")
    private Long id;
    @SerializedName("Image")
    private String image;
    @Expose
    private String name;
    @SerializedName("Path")
    private String path;

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.gradeId);
        dest.writeString(this.gradeName);
        dest.writeValue(this.groupId);
        dest.writeString(this.groupName);
        dest.writeValue(this.id);
        dest.writeString(this.image);
        dest.writeString(this.name);
        dest.writeString(this.path);
    }

    public Content() {
    }

    protected Content(Parcel in) {
        this.gradeId = (Long) in.readValue(Long.class.getClassLoader());
        this.gradeName = in.readString();
        this.groupId = (Long) in.readValue(Long.class.getClassLoader());
        this.groupName = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.image = in.readString();
        this.name = in.readString();
        this.path = in.readString();
    }

    public static final Parcelable.Creator<Content> CREATOR = new Parcelable.Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
}
