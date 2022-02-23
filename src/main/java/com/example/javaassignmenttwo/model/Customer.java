package com.example.javaassignmenttwo.model;

public class Customer {

        //---attributes
        private String id;
        private String firstName;
        private String lastName;
        private String country;
        private String postalCode;
        private String phoneNumber;
        private String email;

        //---constructors
        public Customer(){

        }

        public Customer(String id, String fname, String lname, String country, String postalcode, String phone, String email){
            this.id = id;
            this.firstName = fname;
            this.lastName = lname;
            this.country = country;
            this.postalCode = postalcode;
            this.phoneNumber = phone;
            this.email = email;
        }

        //---setters
        public void setCustomerId(String customerID) {
            this.id = customerID;
        }
        public void setCustomerFirstname(String customerFirstname) {
        this.firstName = customerFirstname;
    }
        public void setCustomerLastname(String customerLastname) {
        this.lastName = customerLastname;
    }
        public void setCustomerCountry(String customerCountry) {
        this.country = customerCountry;
    }
        public void setCustomerPostalCode(String customerPostalCode) {
        this.postalCode = customerPostalCode;
    }
        public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.phoneNumber = customerPhoneNumber;
    }
        public void setCustomerEmail(String customerEmail) {
        this.email = customerEmail;
    }

        //---getters
        public String getCustomerId() {
            return id;
        }
        public String getCustomerFirstname() {
        return firstName;
    }
        public String getCustomerLastname() {
        return lastName;
    }
        public String getCustomerCountry() {
        return country;
    }
        public String getCustomerPostalCode() {
        return postalCode;
    }
        public String getCustomerPhoneNumber() {
        return phoneNumber;
    }
        public String getCustomerEmail() {
        return email;
    }




}
