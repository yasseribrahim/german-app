
package com.mazeed.lms.german.learning.app.domain.models.user;

import com.google.gson.annotations.SerializedName;
import com.mazeed.lms.german.learning.app.domain.models.BaseModel;

public class Authorization extends BaseModel {
    @SerializedName("Data")
    private AuthorizationDetails authorizationDetails;

    public AuthorizationDetails getAuthorizationDetails() {
        return authorizationDetails;
    }

    public void setAuthorizationDetails(AuthorizationDetails authorizationDetails) {
        this.authorizationDetails = authorizationDetails;
    }
}
