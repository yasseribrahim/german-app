package com.mazeed.lms.german.learning.app.domain.models.user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Busway {

    @SerializedName("Order")
    private double order;

    @SerializedName("DriverPhone")
    private String driverPhone;

    @SerializedName("BusNumber")
    private String busNumber;

    @SerializedName("Stations")
    private List<Station> stations;

    @SerializedName("DriverId")
    private int driverId;

    @SerializedName("NameArabic")
    private String nameArabic;

    @SerializedName("NameEnglish")
    private String nameEnglish;

    @SerializedName("DriverName")
    private String driverName;

    @SerializedName("DriverEmail")
    private String driverEmail;

    @SerializedName("Id")
    private int id;

    @SerializedName("Code")
    private String code;

    @SerializedName("Name")
    private String name;

    public void setOrder(double order) {
        this.order = order;
    }

    public double getOrder() {
        return order;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setNameArabic(String nameArabic) {
        this.nameArabic = nameArabic;
    }

    public String getNameArabic() {
        return nameArabic;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return
                "Busway{" +
                        "order = '" + order + '\'' +
                        ",driverPhone = '" + driverPhone + '\'' +
                        ",busNumber = '" + busNumber + '\'' +
                        ",stations = '" + stations + '\'' +
                        ",driverId = '" + driverId + '\'' +
                        ",nameArabic = '" + nameArabic + '\'' +
                        ",nameEnglish = '" + nameEnglish + '\'' +
                        ",driverName = '" + driverName + '\'' +
                        ",driverEmail = '" + driverEmail + '\'' +
                        ",id = '" + id + '\'' +
                        ",code = '" + code + '\'' +
                        ",name = '" + name + '\'' +
                        "}";
    }
}