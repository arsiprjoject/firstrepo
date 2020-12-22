package com.arsi.flebie.pojo;

public class Provider
{
    private String address;

    private String capabilities;

    private String gender;

    private String lattitude;

    private String city;

    private String kcUserId;

    private String serviceName;

    private String emailAddress;

    private String phoneNumber;

    private String yearsOfExperience;

    private String name;

    private String id;

    private String longitude;

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getCapabilities ()
    {
        return capabilities;
    }

    public void setCapabilities (String capabilities)
    {
        this.capabilities = capabilities;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
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

    public String getKcUserId ()
    {
        return kcUserId;
    }

    public void setKcUserId (String kcUserId)
    {
        this.kcUserId = kcUserId;
    }

    public String getServiceName ()
    {
        return serviceName;
    }

    public void setServiceName (String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getEmailAddress ()
    {
        return emailAddress;
    }

    public void setEmailAddress (String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber ()
    {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getYearsOfExperience ()
    {
        return yearsOfExperience;
    }

    public void setYearsOfExperience (String yearsOfExperience)
    {
        this.yearsOfExperience = yearsOfExperience;
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
        return "ClassPojo [address = "+address+", capabilities = "+capabilities+", gender = "+gender+", lattitude = "+lattitude+", city = "+city+", kcUserId = "+kcUserId+", serviceName = "+serviceName+", emailAddress = "+emailAddress+", phoneNumber = "+phoneNumber+", yearsOfExperience = "+yearsOfExperience+", name = "+name+", id = "+id+", longitude = "+longitude+"]";
    }
}
