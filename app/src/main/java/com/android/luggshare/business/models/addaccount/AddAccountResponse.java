package com.android.luggshare.business.models.addaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddAccountResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("uid")
    @Expose
    private Object uid;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public AddAccountResponse() {
    }

    /**
     *
     * @param uid
     * @param name
     * @param message
     * @param account
     * @param status
     */
    public AddAccountResponse(Integer status, String message, Object uid, String account, String name) {
        super();
        this.status = status;
        this.message = message;
        this.uid = uid;
        this.account = account;
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getUid() {
        return uid;
    }

    public void setUid(Object uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
