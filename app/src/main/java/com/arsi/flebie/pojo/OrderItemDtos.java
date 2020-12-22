package com.arsi.flebie.pojo;

public class OrderItemDtos {
    private String itemName;



    private String itemPrice;

    private String id;

    private String itemIdentifier;

    private String itemProvider;

    private String itemProviderName;
    private String itemDetails;

    private String patientServiceInfo;

    public String getPatientServiceInfo() {
        return patientServiceInfo;
    }

    public void setPatientServiceInfo(String patientServiceInfo) {
        this.patientServiceInfo = patientServiceInfo;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

    public String getItemProviderName() {
        return itemProviderName;
    }

    public void setItemProviderName(String itemProviderName) {
        this.itemProviderName = itemProviderName;
    }

    private String enabled;

    public String getItemName ()
    {
        return itemName;
    }

    public void setItemName (String itemName)
    {
        this.itemName = itemName;
    }


    public String getItemPrice ()
    {
        return itemPrice;
    }

    public void setItemPrice (String itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getItemIdentifier ()
    {
        return itemIdentifier;
    }

    public void setItemIdentifier (String itemIdentifier)
    {
        this.itemIdentifier = itemIdentifier;
    }

    public String getItemProvider ()
    {
        return itemProvider;
    }

    public void setItemProvider (String itemProvider)
    {
        this.itemProvider = itemProvider;
    }

    public String getEnabled ()
    {
        return enabled;
    }

    public void setEnabled (String enabled)
    {
        this.enabled = enabled;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [itemName = "+itemName+", itemPrice = "+itemPrice+", id = "+id+", itemIdentifier = "+itemIdentifier+", itemProvider = "+itemProvider+", enabled = "+enabled+"]";
    }
}
