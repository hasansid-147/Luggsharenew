package com.android.luggshare.business.models.userreviews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddReviews {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("fromuid")
    @Expose
    private Integer fromuid;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("offerid")
    @Expose
    private Integer offerid;

    /**
     * No args constructor for use in serialization
     *
     */
    public AddReviews() {
    }

    /**
     *
     * @param uid
     * @param comments
     * @param fromuid
     * @param rating
     */
    public AddReviews(Integer uid, Integer fromuid, Integer rating, String comments) {
        super();
        this.uid = uid;
        this.fromuid = fromuid;
        this.rating = rating;
        this.comments = comments;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFromuid() {
        return fromuid;
    }

    public void setFromuid(Integer fromuid) {
        this.fromuid = fromuid;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getOfferid() {
        return offerid;
    }

    public void setOfferid(Integer offerid) {
        this.offerid = offerid;
    }

}
