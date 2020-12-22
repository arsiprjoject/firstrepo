package com.arsi.flebie.pojo;

class ProviderDto {

    private String providerDetailsId;

    private String name;

    private String kcUserId;

    public String getProviderDetailsId ()
    {
        return providerDetailsId;
    }

    public void setProviderDetailsId (String providerDetailsId)
    {
        this.providerDetailsId = providerDetailsId;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getKcUserId ()
    {
        return kcUserId;
    }

    public void setKcUserId (String kcUserId)
    {
        this.kcUserId = kcUserId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [providerDetailsId = "+providerDetailsId+", name = "+name+", kcUserId = "+kcUserId+"]";
    }
}
