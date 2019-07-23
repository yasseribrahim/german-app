
package com.mazeed.lms.german.learning.app.domain.models.user;

import com.google.gson.annotations.Expose;

public class AuthorizationDetails {
    @Expose
    private String accessToken;
    @Expose
    private Double expiresIn;
    @Expose
    private String requertAt;
    @Expose
    private String tokeyType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Double getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Double expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRequertAt() {
        return requertAt;
    }

    public void setRequertAt(String requertAt) {
        this.requertAt = requertAt;
    }

    public String getTokeyType() {
        return tokeyType;
    }

    public void setTokeyType(String tokeyType) {
        this.tokeyType = tokeyType;
    }
}
