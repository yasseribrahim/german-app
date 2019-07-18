package com.mazeed.lms.german.learning.app.presentation.ui.utils.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yasser.Ibrahim on 6/21/2018.
 */
public class BottomSheetItem implements Parcelable {
    private int id;
    private int contentId;
    private int iconId;

    public BottomSheetItem(int id, int contentId, int iconId) {
        this.id = id;
        this.contentId = contentId;
        this.iconId = iconId;
    }

    public BottomSheetItem(Parcel in) {
        this.id = in.readInt();
        this.contentId = in.readInt();
        this.iconId = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
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
                ", contentId=" + contentId +
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
        dest.writeInt(this.contentId);
        dest.writeInt(this.iconId);
        dest.writeInt(this.CONTENTS_FILE_DESCRIPTOR);
        dest.writeInt(this.PARCELABLE_WRITE_RETURN_VALUE);
    }

    public static final Creator CREATOR = new Creator() {
        public BottomSheetItem createFromParcel(Parcel in) {
            return new BottomSheetItem(in);
        }

        public BottomSheetItem[] newArray(int size) {
            return new BottomSheetItem[size];
        }
    };
}
