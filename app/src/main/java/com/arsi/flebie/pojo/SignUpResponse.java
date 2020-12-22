package com.arsi.flebie.pojo;

public class SignUpResponse {

    String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "SignUpResponse{" +
                "access_token='" + access_token + '\'' +
                '}';
    }
}
