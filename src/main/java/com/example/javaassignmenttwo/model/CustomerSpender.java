package com.example.javaassignmenttwo.model;

public class CustomerSpender
{
    private String firstname;
    private String lastname;
    private double totalSpent;

    public CustomerSpender(String firstname, String lastname, double totalSpent)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalSpent = totalSpent;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }
}
