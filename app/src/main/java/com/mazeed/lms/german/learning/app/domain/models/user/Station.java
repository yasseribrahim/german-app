
package com.mazeed.lms.german.learning.app.domain.models.user;

import com.google.gson.annotations.SerializedName;

public class Station {

    @SerializedName("GoTime")
    private String goTime;
    @SerializedName("Id")
    private long id;
    @SerializedName("Latitude")
    private double latitude;
    @SerializedName("Longitude")
    private double longitude;
    @SerializedName("NameArabic")
    private String nameArabic;
    @SerializedName("NameEnglish")
    private String nameEnglish;
    @SerializedName("Note")
    private String note;
    @SerializedName("ReturnTime")
    private String returnTime;

    public String getGoTime() {
        return goTime;
    }

    public void setGoTime(String goTime) {
        this.goTime = goTime;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNameArabic() {
        return nameArabic;
    }

    public void setNameArabic(String nameArabic) {
        this.nameArabic = nameArabic;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

}
