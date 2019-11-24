package com.android.luggshare.business.models.userprofile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmailVerification {
    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("email")
    @Expose
    private String email;

    /**
     * No args constructor for use in serialization
     *
     */
    public EmailVerification() {
    }

    /**
     *
     * @param uid
     * @param email
     */
    public EmailVerification(Integer uid, String email) {
        super();
        this.uid = uid;
        this.email = email;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
