package com.android.luggshare.common.bundle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ViewUserPhoneBundle implements Serializable {

    @SerializedName("phone")
    @Expose
    private String phone;

    /**
     * No args constructor for use in serialization
     *
     */
    public ViewUserPhoneBundle() {
    }

    /**
     *
     * @param phone
     */
    public ViewUserPhoneBundle(String phone) {
        super();
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
