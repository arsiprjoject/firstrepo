package com.arsi.flebie.pojo;

public class MyErrorMessage {

    String error;
    String error_description;

    public MyErrorMessage() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    @Override
    public String toString() {
        return "MyErrorMessage{" +
                "error='" + error + '\'' +
                ", error_description='" + error_description + '\'' +
                '}';
    }
}
