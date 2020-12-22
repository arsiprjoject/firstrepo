package com.arsi.flebie.pojo;

public class OrderPatientDetailsDtos {
    private OrderItemDtos[] orderItemDtos;

    private String phoneNumber;

    private String comments;

    private String gender;
    private String patientServiceStatus;
    private String patientServiceInfo;

    private String name;

    private String id;

    private String age;

    private String email;

    public OrderItemDtos[] getOrderItemDtos ()
    {
        return orderItemDtos;
    }

    public String getPatientServiceInfo() {
        return patientServiceInfo;
    }

    public void setPatientServiceInfo(String patientServiceInfo) {
        this.patientServiceInfo = patientServiceInfo;
    }

    public void setOrderItemDtos (OrderItemDtos[] orderItemDtos)
    {
        this.orderItemDtos = orderItemDtos;
    }

    public String getPhoneNumber ()
    {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getComments ()
    {
        return comments;
    }

    public void setComments (String comments)
    {
        this.comments = comments;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }



    public String getPatientServiceStatus ()
    {
        return patientServiceStatus;
    }

    public void setPatientServiceStatus (String patientServiceStatus)
    {
        this.patientServiceStatus = patientServiceStatus;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAge ()
    {
        return age;
    }

    public void setAge (String age)
    {
        this.age = age;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [orderItemDtos = "+orderItemDtos+", phoneNumber = "+phoneNumber+", comments = "+comments+", gender = "+gender+", patientServiceStatus = "+patientServiceStatus+", name = "+name+", id = "+id+", age = "+age+", email = "+email+"]";
    }
}
