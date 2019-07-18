package com.mazeed.lms.german.learning.app.domain.models.user;

import com.mazeed.lms.german.learning.app.domain.models.Language;
import com.mazeed.lms.german.learning.app.domain.utils.UserTypes;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class User {

    @SerializedName("Email")
    private String email;

    @SerializedName("Busways")
    private List<Busway> busways;

    @SerializedName("Gender")
    private boolean gender;

    @SerializedName("CreateDate")
    private String createDate;

    @SerializedName("PhotoUrl")
    private String photoUrl;

    @SerializedName("AppToken")
    private String appToken;

    @SerializedName("NormalizedUserName")
    private String normalizedUserName;

    @SerializedName("PhoneNumberConfirmed")
    private boolean phoneNumberConfirmed;

    @SerializedName("Claims")
    private List<Object> claims;

    @SerializedName("UserName")
    private String userName;

    @SerializedName("IsEnabled")
    private boolean isEnabled;

    @SerializedName("UserRoles")
    private Object userRoles;

    @SerializedName("Roles")
    private List<Object> roles;

    @SerializedName("EmailConfirmed")
    private boolean emailConfirmed;

    @SerializedName("IsDriver")
    private boolean isDriver;

    @SerializedName("Logins")
    private List<Object> logins;

    @SerializedName("FullName")
    private String fullName;

    @SerializedName("AccessFailedCount")
    private int accessFailedCount;

    @SerializedName("PhoneNumber")
    private String phoneNumber;

    @SerializedName("LockoutEndDateUtc")
    private String lockoutEndDateUtc;

    @SerializedName("NormalizedEmail")
    private String normalizedEmail;

    @SerializedName("PasswordHash")
    private String passwordHash;

    @SerializedName("Id")
    private int id;

    @SerializedName("UserType")
    private int userType;

    @SerializedName("TwoFactorEnabled")
    private boolean twoFactorEnabled;

    @SerializedName("SecurityStamp")
    private String securityStamp;

    @SerializedName("LockoutEnabled")
    private boolean lockoutEnabled;

    private String password;
    private Authorization authorization;
    private Language language;

    public User(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBusways(List<Busway> busways) {
        this.busways = busways;
    }

    public List<Busway> getBusways() {
        return busways;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isGender() {
        return gender;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setNormalizedUserName(String normalizedUserName) {
        this.normalizedUserName = normalizedUserName;
    }

    public String getNormalizedUserName() {
        return normalizedUserName;
    }

    public void setPhoneNumberConfirmed(boolean phoneNumberConfirmed) {
        this.phoneNumberConfirmed = phoneNumberConfirmed;
    }

    public boolean isPhoneNumberConfirmed() {
        return phoneNumberConfirmed;
    }

    public void setClaims(List<Object> claims) {
        this.claims = claims;
    }

    public List<Object> getClaims() {
        return claims;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setUserRoles(Object userRoles) {
        this.userRoles = userRoles;
    }

    public Object getUserRoles() {
        return userRoles;
    }

    public void setRoles(List<Object> roles) {
        this.roles = roles;
    }

    public List<Object> getRoles() {
        return roles;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setIsDriver(boolean isDriver) {
        this.isDriver = isDriver;
    }

    public boolean isIsDriver() {
        return isDriver;
    }

    public void setLogins(List<Object> logins) {
        this.logins = logins;
    }

    public List<Object> getLogins() {
        return logins;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setAccessFailedCount(int accessFailedCount) {
        this.accessFailedCount = accessFailedCount;
    }

    public int getAccessFailedCount() {
        return accessFailedCount;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setLockoutEndDateUtc(String lockoutEndDateUtc) {
        this.lockoutEndDateUtc = lockoutEndDateUtc;
    }

    public String getLockoutEndDateUtc() {
        return lockoutEndDateUtc;
    }

    public void setNormalizedEmail(String normalizedEmail) {
        this.normalizedEmail = normalizedEmail;
    }

    public String getNormalizedEmail() {
        return normalizedEmail;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }

    public boolean isDriver() {
        return userType == UserTypes.USER_TYPE_DRIVER;
    }

    public boolean isStudent() {
        return userType == UserTypes.USER_TYPE_STUDENT;
    }

    public boolean isParent() {
        return userType == UserTypes.USER_TYPE_PARENT;
    }

    public boolean isSupportedUser() {
        return isDriver() || isStudent() || isParent();
    }

    public void setTwoFactorEnabled(boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    public boolean isTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    public void setSecurityStamp(String securityStamp) {
        this.securityStamp = securityStamp;
    }

    public String getSecurityStamp() {
        return securityStamp;
    }

    public void setLockoutEnabled(boolean lockoutEnabled) {
        this.lockoutEnabled = lockoutEnabled;
    }

    public boolean isLockoutEnabled() {
        return lockoutEnabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    public boolean isLogged() {
        return authorization != null;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public boolean isAppTokenChanged(String token) {
        return appToken != null && !appToken.equals(token);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return
                "User{" +
                        "email = '" + email + '\'' +
                        ",busways = '" + busways + '\'' +
                        ",gender = '" + gender + '\'' +
                        ",createDate = '" + createDate + '\'' +
                        ",photoUrl = '" + photoUrl + '\'' +
                        ",appToken = '" + appToken + '\'' +
                        ",normalizedUserName = '" + normalizedUserName + '\'' +
                        ",phoneNumberConfirmed = '" + phoneNumberConfirmed + '\'' +
                        ",claims = '" + claims + '\'' +
                        ",userName = '" + userName + '\'' +
                        ",isEnabled = '" + isEnabled + '\'' +
                        ",userRoles = '" + userRoles + '\'' +
                        ",roles = '" + roles + '\'' +
                        ",emailConfirmed = '" + emailConfirmed + '\'' +
                        ",isDriver = '" + isDriver + '\'' +
                        ",logins = '" + logins + '\'' +
                        ",fullName = '" + fullName + '\'' +
                        ",accessFailedCount = '" + accessFailedCount + '\'' +
                        ",phoneNumber = '" + phoneNumber + '\'' +
                        ",lockoutEndDateUtc = '" + lockoutEndDateUtc + '\'' +
                        ",normalizedEmail = '" + normalizedEmail + '\'' +
                        ",passwordHash = '" + passwordHash + '\'' +
                        ",id = '" + id + '\'' +
                        ",userType = '" + userType + '\'' +
                        ",twoFactorEnabled = '" + twoFactorEnabled + '\'' +
                        ",securityStamp = '" + securityStamp + '\'' +
                        ",lockoutEnabled = '" + lockoutEnabled + '\'' +
                        "}";
    }
}