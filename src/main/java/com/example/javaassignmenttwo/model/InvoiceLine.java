package com.example.javaassignmenttwo.model;

public class InvoiceLine {

    //---attributes
    private String invoiceId;
    private String trackId;

    //---constructors
    public InvoiceLine(String invoiceID, String trackID){
        this.invoiceId = invoiceID;
        this.trackId = trackID;
    }

    //---setters
    public void setInvoiceId(String invoiceID) {
        this.invoiceId = invoiceID;
    }
    public void setTrackId(String trackID) {
        this.trackId = trackID;
    }

    //---getters
    public String getInvoiceId() {
        return this.invoiceId;
    }
    public String getTrackId() {
        return this.trackId;
    }
}
