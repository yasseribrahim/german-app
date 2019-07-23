
package com.mazeed.lms.german.learning.app.domain.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mazeed.lms.german.learning.app.domain.models.Language;

public class User {

    @SerializedName("Address")
    private String address;
    @SerializedName("Email")
    private String email;
    @Expose
    private String firstname;
    @SerializedName("ImagePath")
    private String imagePath;
    @Expose
    private String lastname;
    @SerializedName("Mobile")
    private String mobile;
    @SerializedName("Password")
    private String password;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Username")
    private String username;

    private AuthorizationDetails authorization;
    private Language language;

    public User() {
    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorization(AuthorizationDetails authorization) {
        this.authorization = authorization;
    }

    public AuthorizationDetails getAuthorization() {
        return authorization;
    }

    public String getToken() {
        return authorization.getTokeyType() + " " + authorization.getAccessToken();
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public boolean isLogged() {
        return authorization != null;
    }
}
