package com.android.luggshare.business.models.claim;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClaimRequest {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("itemname")
    @Expose
    private String itemname;
    @SerializedName("requesttype")
    @Expose
    private String requesttype;
    @SerializedName("offerid")
    @Expose
    private Integer offerid;

    /**
     * No args constructor for use in serialization
     *
     */
    public ClaimRequest() {
    }

    /**
     *
     * @param requesttype
     * @param itemname
     * @param offerid
     * @param username
     */
    public ClaimRequest(String username, String itemname, String requesttype, Integer offerid) {
        super();
        this.username = username;
        this.itemname = itemname;
        this.requesttype = requesttype;
        this.offerid = offerid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getRequesttype() {
        return requesttype;
    }

    public void setRequesttype(String requesttype) {
        this.requesttype = requesttype;
    }

    public Integer getOfferid() {
        return offerid;
    }

    public void setOfferid(Integer offerid) {
        this.offerid = offerid;
    }

}
