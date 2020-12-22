package com.arsi.flebie.pojo;

public class PatientDetail {
    private String pincode;

    private String address;

    private OrderPatientDetailsDtos[] orderPatientDetailsDtos;

    private String lattitude;

    private String city;

    private String scheduledDate;

    private OrderVisitAssignmentDtos[] orderVisitAssignmentDtos;

    private String label;

    private String visitComments;

    private String timeslot;

    private String orderVisitStatus;

    private String id;

    private String longitude;

    private float orderPrice;

    public String getPincode ()
    {
        return pincode;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setPincode (String pincode)
    {
        this.pincode = pincode;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }


    public OrderPatientDetailsDtos[] getOrderPatientDetailsDtos ()
    {
        return orderPatientDetailsDtos;
    }

    public void setOrderPatientDetailsDtos (OrderPatientDetailsDtos[] orderPatientDetailsDtos)
    {
        this.orderPatientDetailsDtos = orderPatientDetailsDtos;
    }

    public String getLattitude ()
    {
        return lattitude;
    }

    public void setLattitude (String lattitude)
    {
        this.lattitude = lattitude;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getScheduledDate ()
    {
        return scheduledDate;
    }

    public void setScheduledDate (String scheduledDate)
    {
        this.scheduledDate = scheduledDate;
    }

    public OrderVisitAssignmentDtos[] getOrderVisitAssignmentDtos ()
    {
        return orderVisitAssignmentDtos;
    }

    public void setOrderVisitAssignmentDtos (OrderVisitAssignmentDtos[] orderVisitAssignmentDtos)
    {
        this.orderVisitAssignmentDtos = orderVisitAssignmentDtos;
    }

    public String getLabel ()
    {
        return label;
    }

    public void setLabel (String label)
    {
        this.label = label;
    }

    public String getVisitComments ()
    {
        return visitComments;
    }

    public void setVisitComments (String visitComments)
    {
        this.visitComments = visitComments;
    }

    public String getTimeslot ()
    {
        return timeslot;
    }

    public void setTimeslot (String timeslot)
    {
        this.timeslot = timeslot;
    }

    public String getOrderVisitStatus ()
    {
        return orderVisitStatus;
    }

    public void setOrderVisitStatus (String orderVisitStatus)
    {
        this.orderVisitStatus = orderVisitStatus;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
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
        return "ClassPojo [pincode = "+pincode+", address = "+address+", orderPatientDetailsDtos = "+orderPatientDetailsDtos+", lattitude = "+lattitude+", city = "+city+", scheduledDate = "+scheduledDate+", orderVisitAssignmentDtos = "+orderVisitAssignmentDtos+", label = "+label+", visitComments = "+visitComments+", timeslot = "+timeslot+", orderVisitStatus = "+orderVisitStatus+", id = "+id+", longitude = "+longitude+"]";
    }
}
