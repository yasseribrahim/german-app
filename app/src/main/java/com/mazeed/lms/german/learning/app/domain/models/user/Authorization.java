
package com.mazeed.lms.german.learning.app.domain.models.user;

import com.google.gson.annotations.SerializedName;

public class  Authorization {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName(".expires")
    private String expires;
    @SerializedName("expires_in")
    private Long expiresIn;
    @SerializedName(".issued")
    private String issued;
    @SerializedName("Role")
    private String role;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("UserId")
    private String userId;
    @SerializedName("UserName")
    private String userName;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return tokenType + " " + accessToken;
    }
}
