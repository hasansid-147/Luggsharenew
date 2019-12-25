package com.android.luggshare.business.services;

import com.android.luggshare.business.models.UpdateOfferStatus.UpdateOfferStatus;
import com.android.luggshare.business.models.UpdateOfferStatus.UpdateOfferStatusResponse;
import com.android.luggshare.business.models.acceptoffer.OfferAcceptRequest;
import com.android.luggshare.business.models.acceptoffer.OfferAcceptResponse;
import com.android.luggshare.business.models.addaccount.AddAccountRequest;
import com.android.luggshare.business.models.addaccount.AddAccountResponse;
import com.android.luggshare.business.models.addcard.AddCardRequest;
import com.android.luggshare.business.models.addcard.AddCardResponse;
import com.android.luggshare.business.models.claim.ClaimRequest;
import com.android.luggshare.business.models.editoffer.EditOfferReponse;
import com.android.luggshare.business.models.editoffer.EditOfferRequest;
import com.android.luggshare.business.models.getmyofferspending.MyOffersPendingListRequestModel;
import com.android.luggshare.business.models.getmyofferspending.MyOffersPendingListResponseModel;
import com.android.luggshare.business.models.getmyoffersreceived.MyOffersReceivedListRequestModel;
import com.android.luggshare.business.models.getmyoffersreceived.MyOffersReceivedListResponseModel;
import com.android.luggshare.business.models.getsenderlist.ListResponse;
import com.android.luggshare.business.models.getsenderlist.RequestSenderList;
import com.android.luggshare.business.models.loginservice.LoginRequest;
import com.android.luggshare.business.models.loginservice.LoginResponse;
import com.android.luggshare.business.models.notifications.GetNotifications;
import com.android.luggshare.business.models.notifications.NotificationReponse;
import com.android.luggshare.business.models.purchaserdetails.PurchaserDetailsReponse;
import com.android.luggshare.business.models.purchaserdetails.PurchaserDetailsRequest;
import com.android.luggshare.business.models.purchasersummary.PurchaserSummaryRequest;
import com.android.luggshare.business.models.purchasersummary.PurchaserSummaryResponse;
import com.android.luggshare.business.models.registrationservice.SignUpResponse;
import com.android.luggshare.business.models.sender.SenderRequest;
import com.android.luggshare.business.models.senderdetails.SenderDetailsRequest;
import com.android.luggshare.business.models.senderdetails.SenderDetailsResponse;
import com.android.luggshare.business.models.sendersummary.SenderSummaryRequest;
import com.android.luggshare.business.models.sendersummary.SenderSummaryResponse;
import com.android.luggshare.business.models.tracking.GetLatestTracking;
import com.android.luggshare.business.models.tracking.GetLatestTrackingResp;
import com.android.luggshare.business.models.tracking.GetTrackList;
import com.android.luggshare.business.models.tracking.TrackListResponse;
import com.android.luggshare.business.models.tracking.UpdateTracking;
import com.android.luggshare.business.models.tracking.UpdateTrackingResp;
import com.android.luggshare.business.models.traveler.TravelerRequest;
import com.android.luggshare.business.models.traveler.TravelerResponse;
import com.android.luggshare.business.models.travelerdetails.TravelerDetailsRequest;
import com.android.luggshare.business.models.travelerdetails.TravelerDetailsResponse;
import com.android.luggshare.business.models.travelerlisting.TravListingRequest;
import com.android.luggshare.business.models.travelerlisting.TravListingResponse;
import com.android.luggshare.business.models.traveleroffer.TravelerOfferRequest;
import com.android.luggshare.business.models.traveleroffer.TravelerOfferResponse;
import com.android.luggshare.business.models.userprofile.EmailVerification;
import com.android.luggshare.business.models.userprofile.EmailVerificationResponse;
import com.android.luggshare.business.models.userprofile.UpdUserProfile;
import com.android.luggshare.business.models.userprofile.UpdUserProfileResponse;
import com.android.luggshare.business.models.userprofile.UserProfileGet;
import com.android.luggshare.business.models.userprofile.UserProfileResponse;
import com.android.luggshare.business.models.userreviews.AddReviews;
import com.android.luggshare.business.models.userreviews.AddReviewsReponse;
import com.android.luggshare.business.models.userreviews.GetReviews;
import com.android.luggshare.business.models.userreviews.ReviewsListReponse;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiInterface {

    /*@POST("User/createuser")
    Call<SignUpResponse> UserRegistration(@Body SignUpRequest body);*/

    @Multipart
    @POST("User/createuser")
    Call<SignUpResponse> userRegistration(@Part("fname") RequestBody fname,
                                          @Part("lname") RequestBody lname,
                                          @Part("email") RequestBody email,
                                          @Part("password") RequestBody password,
                                          @Part("phone") RequestBody phone,
                                          @Part("phone_ivalid") RequestBody phone_ivalid,
                                          @Part MultipartBody.Part img);

    @POST("User/login")
    Call<LoginResponse> UserLogin(@Body LoginRequest body);

    @POST("dashboard/get")
    Call<ArrayList<ListResponse>> fetchListData(@Body RequestSenderList body);

    @POST("offer/offerlist_rcvd")
    Call<ArrayList<MyOffersReceivedListResponseModel>> fetchReceivedOffersData(@Body MyOffersReceivedListRequestModel body);

    @POST("offer/offerlist")
    Call<ArrayList<MyOffersPendingListResponseModel>> fetchPendingOffersData(@Body MyOffersPendingListRequestModel body);

    @POST("dashboard/senderdetail")
    Call<SenderDetailsResponse> fetchSenderDetails(@Body SenderDetailsRequest body);

    @POST("offer/editoffer")
    Call<EditOfferReponse> editOffer(@Body EditOfferRequest body);

    @POST("offer/acceptoffer")
    Call<OfferAcceptResponse> acceptOffer(@Body OfferAcceptRequest body);

    @POST("payment/addacc")
    Call<AddAccountResponse> addAccount(@Body AddAccountRequest body);

    @POST("payment/addcrc")
    Call<AddCardResponse> addCard(@Body AddCardRequest body);

    @POST("dashboard/travelerdetail")
    Call<TravelerDetailsResponse> fetchTravelerDetails(@Body TravelerDetailsRequest body);

    @POST("Summary/sender")
    Call<SenderSummaryResponse> getPricing(@Body SenderSummaryRequest body);

    @POST("request/sender")
    Call<Object> senderRequest(@Body SenderRequest body);

    @Multipart
    @POST("request/sender")
    Call<Object> senderRequestNew(@Part("uid") RequestBody uid,
                                  @Part("req_typ") RequestBody reqType,
                                  @Part("image") RequestBody img,
                                  @Part("name") RequestBody name,
                                  @Part("description") RequestBody desc,
                                  @Part("weight") RequestBody weight,
                                  @Part("size") RequestBody size,
                                  @Part("from_country") RequestBody fromCountry,
                                  @Part("from_city") RequestBody fromCity,
                                  @Part("to_country") RequestBody toCountry,
                                  @Part("to_city") RequestBody toCity,
                                  @Part("del_date") RequestBody delDate,
                                  @Part("bringer_rewrd") RequestBody bringer_rewrd,
                                  @Part("service_fee") RequestBody service_fee,
                                  @Part("item_cat1") RequestBody item1,
                                  @Part("item_cat2") RequestBody item2,
                                  @Part("item_cat3") RequestBody item3,
                                  @Part("item_cat4") RequestBody item4,
                                  @Part("item_cat5") RequestBody item5,
                                  @Part("item_cat6") RequestBody item6,
                                  @Part MultipartBody.Part img1,
                                  @Part MultipartBody.Part img2);
    @POST("request/traveler")
    Call<TravelerResponse> travelerRequest(@Body TravelerRequest body);

    @POST("srchrequest/fromTraveler")
    Call<ArrayList<TravListingResponse>> travelerList(@Body TravListingRequest body);

    @POST("Summary/purchaser")
    Call<PurchaserSummaryResponse> getpurchaserPricing(@Body PurchaserSummaryRequest body);

    @POST("dashboard/purchaserdetail")
    Call<PurchaserDetailsReponse> fetchPurchaserDetails(@Body PurchaserDetailsRequest body);


    @Multipart
    @POST("request/purchaser")
    Call<Object> PurchaserRequest(@Part("uid") RequestBody uid,
                                  @Part("req_typ") RequestBody reqType,
                                  @Part("url") RequestBody url,
                                  @Part("prod_detail") RequestBody proddetail,
                                  @Part("prod_name") RequestBody prodname,
                                  @Part("price") RequestBody price,
                                  @Part("quantity") RequestBody quantity,
                                  @Part("from_country") RequestBody fromCountry,
                                  @Part("from_city") RequestBody fromCity,
                                  @Part("to_country") RequestBody to_Country,
                                  @Part("to_city") RequestBody toCity,
                                  @Part("del_date") RequestBody delDate,
                                  @Part("bringer_rewrd") RequestBody bringer_rewrd,
                                  @Part MultipartBody.Part img1);


    @POST("User/getprofile")
    Call<UserProfileResponse> getUserProfile(@Body UserProfileGet body);


    @POST("User/updprofile")
    Call<UpdUserProfileResponse> updUserProfile(@Body UpdUserProfile body);

    @POST("email/activate")
    Call<EmailVerificationResponse> verifyEmailaddress(@Body EmailVerification body);


    @POST("offer/createoffer")
    Call<TravelerOfferResponse> sendTravelerOffer(@Body TravelerOfferRequest body);


    @POST("track/gettracklist")
    Call<ArrayList<TrackListResponse>> fetchTrackListData(@Body GetTrackList body);

    @POST("track/getlatest")
    Call<GetLatestTrackingResp> getLatestTracking(@Body GetLatestTracking body);

    @POST("track/requestlates")
    Call<UpdateTrackingResp> updateTracking(@Body UpdateTracking body);

    @POST("track/requestlates")
    Call<String> requestLatest(Integer id);


    @POST("notification/get")
    Call<ArrayList<NotificationReponse>> fetchNotifications(@Body GetNotifications body);




    @POST("reviews/getreviews")
    Call<ArrayList<ReviewsListReponse>> fetchAllReviews(@Body GetReviews body);

    @POST("reviews/addreviews")
    Call<AddReviewsReponse> AddReviews(@Body AddReviews body);


    @POST("payment/delconf")
    Call<UpdateOfferStatusResponse> deliveryConfirm(@Body UpdateOfferStatus body);

    @POST("claim/raiseclaim")
    Call<String> raiseClaim(@Body ClaimRequest body);

}