package com.arsi.flebie.pojo;

public  class VisitMasterDto {
    private String address;

    private String orderId;

    private String lattitude;

    private String orderVisitId;

    private String timeslot;

    private String tenantId;

    private String scheduledDate;

    private String orderIdentifier;
    private int numberOfPatients;


    private String longitude;

    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getOrderId ()
    {
        return orderId;
    }

    public void setOrderId (String orderId)
    {
        this.orderId = orderId;
    }

    public String getLattitude ()
    {
        return lattitude;
    }

    public void setLattitude (String lattitude)
    {
        this.lattitude = lattitude;
    }

    public String getOrderVisitId ()
    {
        return orderVisitId;
    }

    public void setOrderVisitId (String orderVisitId)
    {
        this.orderVisitId = orderVisitId;
    }

    public String getTimeslot ()
    {
        return timeslot;
    }

    public void setTimeslot (String timeslot)
    {
        this.timeslot = timeslot;
    }

    public String getTenantId ()
    {
        return tenantId;
    }

    public void setTenantId (String tenantId)
    {
        this.tenantId = tenantId;
    }

    public String getScheduledDate ()
    {
        return scheduledDate;
    }

    public void setScheduledDate (String scheduledDate)
    {
        this.scheduledDate = scheduledDate;
    }

    public String getOrderIdentifier ()
    {
        return orderIdentifier;
    }

    public void setOrderIdentifier (String orderIdentifier)
    {
        this.orderIdentifier = orderIdentifier;
    }


    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [address = "+address+", orderId = "+orderId+", lattitude = "+lattitude+", orderVisitId = "+orderVisitId+", timeslot = "+timeslot+", tenantId = "+tenantId+", scheduledDate = "+scheduledDate+", orderIdentifier = "+orderIdentifier+", serviceName = "+", longitude = "+longitude+"]";
    }
}
