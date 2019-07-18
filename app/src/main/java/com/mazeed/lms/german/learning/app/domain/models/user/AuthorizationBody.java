package com.mazeed.lms.german.learning.app.domain.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorizationBody {
    @SerializedName("grant_type")
    @Expose
    private String grantType;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;

    public AuthorizationBody() {
    }

    public AuthorizationBody(String grantType, String username, String password) {
        this.grantType = grantType;
        this.username = username;
        this.password = password;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthorizationBody{" +
                "grantType='" + grantType + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
