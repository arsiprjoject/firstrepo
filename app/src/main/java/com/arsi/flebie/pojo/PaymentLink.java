package com.arsi.flebie.pojo;

public class PaymentLink {

    private String flebieOrderId;

    private String transactionMethod;

    private String transactionStatus;

    private String transactionAmount;

    private String invoiceId;

    private String id;

    private String externalOrderId;

    private String paymentLink;

    public String getFlebieOrderId ()
    {
        return flebieOrderId;
    }

    public void setFlebieOrderId (String flebieOrderId)
    {
        this.flebieOrderId = flebieOrderId;
    }

    public String getTransactionMethod ()
    {
        return transactionMethod;
    }

    public void setTransactionMethod (String transactionMethod)
    {
        this.transactionMethod = transactionMethod;
    }

    public String getTransactionStatus ()
    {
        return transactionStatus;
    }

    public void setTransactionStatus (String transactionStatus)
    {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionAmount ()
    {
        return transactionAmount;
    }

    public void setTransactionAmount (String transactionAmount)
    {
        this.transactionAmount = transactionAmount;
    }

    public String getInvoiceId ()
    {
        return invoiceId;
    }

    public void setInvoiceId (String invoiceId)
    {
        this.invoiceId = invoiceId;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getExternalOrderId ()
    {
        return externalOrderId;
    }

    public void setExternalOrderId (String externalOrderId)
    {
        this.externalOrderId = externalOrderId;
    }

    public String getPaymentLink ()
    {
        return paymentLink;
    }

    public void setPaymentLink (String paymentLink)
    {
        this.paymentLink = paymentLink;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [flebieOrderId = "+flebieOrderId+", transactionMethod = "+transactionMethod+", transactionStatus = "+transactionStatus+", transactionAmount = "+transactionAmount+", invoiceId = "+invoiceId+", id = "+id+", externalOrderId = "+externalOrderId+", paymentLink = "+paymentLink+"]";
    }
}
