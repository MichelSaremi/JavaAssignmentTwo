package com.example.javaassignmenttwo;

public class CustomerInvoice {

    //---attributes
    private String invoiceId;
    private String customerId;

    //---constructors
    public CustomerInvoice(String invoiceID, String customerID){
        this.invoiceId = invoiceID;
        this.customerId = customerID;
    }

    //---setters
    public void setInvoiceId(String invoiceID) {
        this.invoiceId = invoiceID;
    }
    public void setCustomerId(String customerID) {
        this.customerId = customerID;
    }

    //---getters
    public String getInvoiceId() {
        return this.invoiceId;
    }
    public String getCustomerId() {
        return this.customerId;
    }
}
