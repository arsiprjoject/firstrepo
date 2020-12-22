package com.arsi.flebie.pojo;

public class AvilabilityRequest {

    private long providerDetailsId;

    private String scheduledDate;

    private String startTime;

    private String endTime;


    public AvilabilityRequest(long providerDetailsId, String scheduledDate, String startTime, String endTime) {
        this.providerDetailsId = providerDetailsId;
        this.scheduledDate = scheduledDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "AvilabilityRequest{" +
                "providerDetailsId=" + providerDetailsId +
                ", scheduledDate='" + scheduledDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
