package com.arsi.flebie;




import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.arsi.flebie.pojo.SignUpResponse;
import com.auth0.android.jwt.JWT;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;



public class TokenAuthenticator implements Interceptor {



    @Override
    public Response intercept(Chain chain) throws IOException {


        SharedPreferences prefs = MyApp.getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String token = prefs.getString("token", null);

        String justToken = prefs.getString("justToken", "");
//        justToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJENW9Qc251WFkyNFlyVzRKbHdwWi1kc2t6VGE3ekpZUWF3YWVsSDJCc0drIn0.eyJqdGkiOiJlNmRhMzgyNy01NzVkLTRkNWUtYTdmNy1mMTU4YTVhNmVmYjkiLCJleHAiOjE2MDc3NTAxMjksIm5iZiI6MCwiaWF0IjoxNjA3NzQ2NTI5LCJpc3MiOiJodHRwOi8vMTM5LjU5LjExLjEzNTo4MDgwL2F1dGgvcmVhbG1zL2ZsZWJpZSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJkMTNlMzVhZi04NmZkLTRlNTYtOTAyMC0zOTE2MzBiMjc2ODkiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJmbGViaWUtZnJvbnRlbmQiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiJjOTJiYzJjZi0yMzJjLTQ1YTktOTdiYS1jNmU5MWY4MTFkZDYiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIk9SREVSLlBBWU1FTlQuQUREIiwiT1JERVIuRURJVCIsIkFWQUlMQUJJTElUWS5FRElUIiwiUFJPVklERVIuQUREIiwiUFJPVklERVIuRURJVCIsIlZJU0lUTUFTVEVSLlZJRVciLCJTRVJWSUNFVFlQRS5WSUVXIiwiQVZBSUxBQklMSVRZLkFERCIsIlRFU1QuVklFVyIsIlBST1ZJREVSLkRFTEVURSIsIk9SREVSLlZJRVciLCJQUk9WSURFUi5WSUVXIiwiQlJBTkNILlZJRVciLCJBVkFJTEFCSUxJVFkuVklFVyIsIkxBQi5WSUVXIiwiTEFCVEVTVC5WSUVXIiwiQ1VTVE9NRVIuVklFVyIsIk9SREVSLkFERCJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsIlJPTEUiOiJQUk9WSURFUiIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6IlByYXNoYW50aCBQcmFzaGFudGgiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJwcmFzaGFudGgiLCJnaXZlbl9uYW1lIjoiUHJhc2hhbnRoIiwiZmFtaWx5X25hbWUiOiJQcmFzaGFudGgiLCJlbWFpbCI6InN1cHBvcnRAZmxlYmllLmNvbSJ9.GkgUvdmBG_3jrvFuOwft9-Vh4und9G-iubDWAa0ezO-fGdt8ZFUxqVTquaDJEALbRR92rC2K4Dqrm-h_m7kl5-4NKJ86VQKwCpCkx4ltrbJz_-iIUvw6wSjG1JJW3PfuKR0ZggagZQllc27FOwe4K7IeydTW6OI0kNfqxlK5Umnxea7P_nkyB7uJSSw_73g8MyuyqdQ1IRRFpi9SQ-gmoUmhNfe7ujx2qkDsyvjdDdIZG8hi2ts-TmZXi-3XwWIe7Jkr6a6xYRMlUle23WcOWk7c5pcO4eWSRV7zjfHRPbM3N51PTcbcCtG04jcqLFAMGnQJSm1KAfT1CFfFMCdGNA";
        JWT jwt = new JWT(justToken);
        boolean isExpired = jwt.isExpired(10);

        Request request = newRequestWithAccessToken(chain.request(), justToken);

        Response response = chain.proceed(request);
        if (isExpired) {
            synchronized (this) {
                String clientNameStr = prefs.getString("clientId", "");
                String userNameStr = prefs.getString("userNameStr", "");
                String passwordStr = prefs.getString("passwordStr", "");

               SignUpResponse resp =  Api.login(clientNameStr).loginSync("flebie-frontend",userNameStr,passwordStr,"password");
               Log.d("----------", resp.toString());
                final String updatedAccessToken = resp.getAccess_token();
                // Retry the request
                return chain.proceed(newRequestWithAccessToken(request, updatedAccessToken));
            }
        }
            // otherwise just pass the original response on
        return response;
    }

    @NonNull
    private Request newRequestWithAccessToken(@NonNull Request request, @NonNull String accessToken) {
        return request.newBuilder()
                .header("Authorization", "Bearer " + accessToken)
                .build();
    }
}
