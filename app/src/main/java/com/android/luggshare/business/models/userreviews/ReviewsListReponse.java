package com.android.luggshare.business.models.userreviews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewsListReponse {

    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("commentdate")
    @Expose
    private String commentdate;

    /**
     * No args constructor for use in serialization
     *
     */
    public ReviewsListReponse() {
    }

    /**
     *
     * @param commentdate
     * @param rating
     * @param comment
     * @param username
     */
    public ReviewsListReponse(Integer rating, String username, String comment, String commentdate) {
        super();
        this.rating = rating;
        this.username = username;
        this.comment = comment;
        this.commentdate = commentdate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(String commentdate) {
        this.commentdate = commentdate;
    }

}