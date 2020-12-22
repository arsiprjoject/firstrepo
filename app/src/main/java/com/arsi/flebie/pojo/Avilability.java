package com.arsi.flebie.pojo;

public class Avilability {

    private long providerDetailsId;

    private String date;

    private String startTime;

    private String endTime;

    public Avilability(long providerDetailsId, String date, String startTime, String endTime) {
        this.providerDetailsId = providerDetailsId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Avilability{" +
                "providerDetailsId=" + providerDetailsId +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public long getProviderDetailsId() {
        return providerDetailsId;
    }

    public void setProviderDetailsId(long providerDetailsId) {
        this.providerDetailsId = providerDetailsId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
