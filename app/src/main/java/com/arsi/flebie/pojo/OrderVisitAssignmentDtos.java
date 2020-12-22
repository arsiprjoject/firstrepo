package com.arsi.flebie.pojo;

public class OrderVisitAssignmentDtos
{
    private String lastUpdated;

    private String providerKcUserId;

    private String providerId;

    private String id;

    private String providerName;

    public String getLastUpdated ()
    {
        return lastUpdated;
    }

    public void setLastUpdated (String lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }

    public String getProviderKcUserId ()
    {
        return providerKcUserId;
    }

    public void setProviderKcUserId (String providerKcUserId)
    {
        this.providerKcUserId = providerKcUserId;
    }

    public String getProviderId ()
    {
        return providerId;
    }

    public void setProviderId (String providerId)
    {
        this.providerId = providerId;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getProviderName ()
    {
        return providerName;
    }

    public void setProviderName (String providerName)
    {
        this.providerName = providerName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [lastUpdated = "+lastUpdated+", providerKcUserId = "+providerKcUserId+", providerId = "+providerId+", id = "+id+", providerName = "+providerName+"]";
    }
}
