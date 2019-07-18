package com.mazeed.lms.german.learning.app.domain.models.video;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Video implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("uri")
    private String url;

    public Video() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    protected Video(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
