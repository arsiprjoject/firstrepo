package com.arsi.flebie.pojo;

public class VisitOrder {
    private String orderPaymentStatus;

    private String visitStatus;

    private VisitMasterDto visitMasterDto;

    private String orderPrice;

    private String branch;

    private ProviderDto providerDto;
    private String orderPhoneNumber;

    public String getOrderPhoneNumber() {
        return orderPhoneNumber;
    }

    public void setOrderPhoneNumber(String orderPhoneNumber) {
        this.orderPhoneNumber = orderPhoneNumber;
    }

    public String getOrderPaymentStatus ()
    {
        return orderPaymentStatus;
    }

    public void setOrderPaymentStatus (String orderPaymentStatus)
    {
        this.orderPaymentStatus = orderPaymentStatus;
    }

    public String getVisitStatus ()
    {
        return visitStatus;
    }

    public void setVisitStatus (String visitStatus)
    {
        this.visitStatus = visitStatus;
    }

    public VisitMasterDto getVisitMasterDto ()
    {
        return visitMasterDto;
    }

    public void setVisitMasterDto (VisitMasterDto visitMasterDto)
    {
        this.visitMasterDto = visitMasterDto;
    }

    public String getOrderPrice ()
    {
        return orderPrice;
    }

    public void setOrderPrice (String orderPrice)
    {
        this.orderPrice = orderPrice;
    }

    public String getBranch ()
    {
        return branch;
    }

    public void setBranch (String branch)
    {
        this.branch = branch;
    }

    public ProviderDto getProviderDto ()
    {
        return providerDto;
    }

    public void setProviderDto (ProviderDto providerDto)
    {
        this.providerDto = providerDto;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [orderPaymentStatus = "+orderPaymentStatus+", visitStatus = "+visitStatus+", visitMasterDto = "+visitMasterDto+", orderPrice = "+orderPrice+", branch = "+branch+", providerDto = "+providerDto+"]";
    }

}
