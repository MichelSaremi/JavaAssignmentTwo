package com.example.javaassignmenttwo.model;

public class CustomerCountry
{
    private String country;
    private int totalNumberOfCustomer;

    public CustomerCountry(String country, int totalNumberOfCustomer)
    {
        this.country = country;
        this.totalNumberOfCustomer = totalNumberOfCustomer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalNumberOfCustomer() {
        return totalNumberOfCustomer;
    }

    public void setTotalNumberOfCustomer(int totalNumberOfCustomer) {
        this.totalNumberOfCustomer = totalNumberOfCustomer;
    }
}
