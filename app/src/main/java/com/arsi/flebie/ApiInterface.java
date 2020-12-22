package com.arsi.flebie;

import android.telecom.Call;

import com.arsi.flebie.pojo.Avilability;
import com.arsi.flebie.pojo.AvilabilityRequest;
import com.arsi.flebie.pojo.PatientDetail;
import com.arsi.flebie.pojo.PaymentLink;
import com.arsi.flebie.pojo.VisitListRequest;
import com.arsi.flebie.pojo.Provider;
import com.arsi.flebie.pojo.SignUpResponse;
import com.arsi.flebie.pojo.VisitOrder;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

public interface ApiInterface {

    @FormUrlEncoded // annotation used in POST type requests
    @POST("/protocol/openid-connect/token")     // API's endpoints
     void login(@Field("client_id") String clientId,
                             @Field("username") String username,
                             @Field("password") String password,
                             @Field("grant_type") String logintype,
                             Callback<SignUpResponse> callback);

    @FormUrlEncoded // annotation used in POST type requests
    @POST("/protocol/openid-connect/token")     // API's endpoints
    SignUpResponse loginSync(@Field("client_id") String clientId,
               @Field("username") String username,
               @Field("password") String password,
               @Field("grant_type") String logintype);

    // API's endpoints
    @Headers({ "Content-Type: application/json; X-TENANT-ID:flebie; charset=UTF-8"})
    @GET("/provider/byToken")
    void getProvider(Callback<Provider> callback);


    // API's endpoints
    @Headers({ "Content-Type: application/json; X-TENANT-ID:flebie; charset=UTF-8"})
    @GET("/order/{orderId}/visit/{visitId}")
    void getVisitList(@Path(value = "orderId", encode = false) String orderId,
                      @Path(value = "visitId", encode = false) String visitId,
                      Callback<PatientDetail> callback);

    @Headers({ "Content-Type: application/json; X-TENANT-ID:flebie; charset=UTF-8"})
    @PUT("/order/{order_id}/visit/{visit_id}/patient/{patient_id}/sampleCollected")
    void sampleCollection(@Path(value = "order_id", encode = false) String order_id,
                      @Path(value = "visit_id", encode = false) String visit_id,
                      @Path(value = "patient_id", encode = false) String patient_id,
                          @Body String emptyString,
                      Callback<Void> callback);

    // API's endpoints
    @Headers({ "Content-Type: application/json"})
    @POST("/availability")
    void setAvilability(@Body AvilabilityRequest body,
                        Callback<Void> callback);


    // API's endpoints
    @GET("/availability/providerDetails/{providerDetailsId}/date/{date}")
    void getAvailability(@Path(value = "providerDetailsId", encode = false) String providerDetailsId,
                      @Path(value = "date", encode = false) String date,
                      Callback<Avilability> callback);


    @DELETE("/availability/providerDetails/{providerDetailsId}/date/{date}")
    void clearAvailablity(@Path(value = "providerDetailsId", encode = false) String providerDetailsId,
                          @Path(value = "date", encode = false) String date,
                          Callback<Boolean> callback);


    // API's endpoints
    @Headers({ "Content-Type: application/json"})
    @POST("/order/provider/{providerDetailsId}/visit")
    void getVisits(@Path(value = "providerDetailsId", encode = false) String userId,
                   @Body VisitListRequest body,
                        Callback<List<VisitOrder>> callback);


    // API's endpoints
    @Headers({ "Content-Type: application/json"})
    @POST("/order/payment/link/{order_id}")
    void sendPayment(@Path(value = "order_id", encode = false) String order_id,
                   Callback<PaymentLink> callback);



    @Headers({ "Content-Type: application/json; X-TENANT-ID:flebie; charset=UTF-8"})
    @PUT("/order/{order_id}/cashPayment")
    void cashReceived(@Path(value = "order_id", encode = false) String order_id,@Body String emptyString,
                          Callback<Void> callback);
//
    @Multipart
    @POST("/client/{clientId}/branch/{branchName}/orderReceipt/{orderIdentifier}")
    void uploadFile(@Path(value = "clientId", encode = false) String clientId,
                    @Path(value = "branchName", encode = false) String branchName,
                    @Path(value = "orderIdentifier", encode = false) String orderIdentifier,
                    @Part("file") TypedFile file,
                    Callback<Void> callback);
}
