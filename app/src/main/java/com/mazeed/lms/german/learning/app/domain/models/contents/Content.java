
package com.mazeed.lms.german.learning.app.domain.models.contents;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Content implements Parcelable {
    @SerializedName("GradeName")
    private String gradeName;
    @SerializedName("Id")
    private Long id;
    @SerializedName("Image")
    private String image;
    @SerializedName("Path")
    private String path;
    @SerializedName("Text")
    private String text;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gradeName);
        dest.writeValue(this.id);
        dest.writeString(this.image);
        dest.writeString(this.path);
        dest.writeString(this.text);
    }

    public Content() {
    }

    protected Content(Parcel in) {
        this.gradeName = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.image = in.readString();
        this.path = in.readString();
        this.text = in.readString();
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
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
