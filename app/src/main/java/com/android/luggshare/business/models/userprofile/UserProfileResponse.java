package com.android.luggshare.business.models.userprofile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileResponse {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("image_pth")
    @Expose
    private String imagePth;
    @SerializedName("islogin")
    @Expose
    private Integer islogin;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("isphone")
    @Expose
    private Integer isphone;
    @SerializedName("isimage")
    @Expose
    private Integer isimage;
    @SerializedName("isemail")
    @Expose
    private Integer isemail;
    @SerializedName("issocial")
    @Expose
    private Integer issocial;
    @SerializedName("iscc")
    @Expose
    private Integer iscc;
    @SerializedName("iscnic")
    @Expose
    private Integer iscnic;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("desc")
    @Expose
    private String desc;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserProfileResponse() {
    }

    /**
     *
     * @param uid
     * @param lname
     * @param phone
     * @param desc
     * @param image
     * @param islogin
     * @param response
     * @param isphone
     * @param isemail
     * @param email
     * @param isimage
     * @param iscnic
     * @param iscc
     * @param imagePth
     * @param rating
     * @param fname
     * @param issocial
     */
    public UserProfileResponse(Integer uid, String fname, String lname, String email, String image, String imagePth, Integer islogin, String phone, Integer isphone, Integer isemail, Integer isimage, Integer issocial, Integer iscc, Integer iscnic, Integer rating, Integer response, String desc) {
        super();
        this.uid = uid;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.image = image;
        this.imagePth = imagePth;
        this.islogin = islogin;
        this.phone = phone;
        this.isphone = isphone;
        this.isemail = isemail;
        this.isimage = isimage;
        this.issocial = issocial;
        this.iscc = iscc;
        this.iscnic = iscnic;
        this.rating = rating;
        this.response = response;
        this.desc = desc;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImagePth() {
        return imagePth;
    }

    public void setImagePth(String imagePth) {
        this.imagePth = imagePth;
    }

    public Integer getIslogin() {
        return islogin;
    }

    public void setIslogin(Integer islogin) {
        this.islogin = islogin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsphone() {
        return isphone;
    }

    public void setIsphone(Integer isphone) {
        this.isphone = isphone;
    }

    public Integer getIsemail() {
        return isemail;
    }

    public void setIsemail(Integer isemail) {
        this.isemail = isemail;
    }



    public Integer getIsimage() {
        return isimage;
    }

    public void setIsimage(Integer isimage) {
        this.isimage = isimage;
    }

    public Integer getIssocial() {
        return issocial;
    }

    public void setIssocial(Integer issocial) {
        this.issocial = issocial;
    }

    public Integer getIscc() {
        return iscc;
    }

    public void setIscc(Integer iscc) {
        this.iscc = iscc;
    }

    public Integer getIscnic() {
        return iscnic;
    }

    public void setIscnic(Integer iscnic) {
        this.iscnic = iscnic;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
