package com.mazeed.lms.german.learning.app.presentation.ui.utils.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yasser.Ibrahim on 6/21/2018.
 */
public class BottomSheetItem implements Parcelable {
    private int id;
    private String content;
    private int iconId;

    public BottomSheetItem() {
    }

    public BottomSheetItem(int id, String content, int iconId) {
        this.id = id;
        this.content = content;
        this.iconId = iconId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BottomSheetItem moreItem = (BottomSheetItem) o;

        return id == moreItem.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "BottomSheetItem{" +
                "id=" + id +
                ", content=" + content +
                ", iconId=" + iconId +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.content);
        dest.writeInt(this.iconId);
    }

    protected BottomSheetItem(Parcel in) {
        this.id = in.readInt();
        this.content = in.readString();
        this.iconId = in.readInt();
    }

    public static final Creator<BottomSheetItem> CREATOR = new Creator<BottomSheetItem>() {
        @Override
        public BottomSheetItem createFromParcel(Parcel source) {
            return new BottomSheetItem(source);
        }

        @Override
        public BottomSheetItem[] newArray(int size) {
            return new BottomSheetItem[size];
        }
    };
}
