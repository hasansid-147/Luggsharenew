package com.android.luggshare.common.bundle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetUserProfileBundle implements Serializable {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("isPreferenceUser")
    @Expose
    private Boolean isPreferenceUser;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetUserProfileBundle() {
    }

    /**
     *
     * @param isPreferenceUser
     * @param uid
     * @param email
     */
    public GetUserProfileBundle(Integer uid, Object email, Boolean isPreferenceUser) {
        super();
        this.uid = uid;
        this.email = email;
        this.isPreferenceUser = isPreferenceUser;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Boolean getIsPreferenceUser() {
        return isPreferenceUser;
    }

    public void setIsPreferenceUser(Boolean isPreferenceUser) {
        this.isPreferenceUser = isPreferenceUser;
    }

}