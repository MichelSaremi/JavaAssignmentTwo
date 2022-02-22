package com.example.javaassignmenttwo;

public class CustomerFavGenre {

    //---attributes
    private String firstname;
    private String lastname;
    private String customerId;
    private String genreId;
    private String genreCount;


    //---constructors
    public CustomerFavGenre(String firstName, String lastName, String customerID, String genreID, String count ){
        this.firstname = firstName;
        this.lastname = lastName;
        this.customerId = customerID;
        this.genreId = genreID;
        this.genreCount = count;
    }

    //---setters
    public void getFirstName(String fname) {this.firstname = fname;}
    public void getLastName(String lname) {
        this.lastname = lname;
    }
    public void getCustomerId(String cid) {this.customerId = cid;}
    public void getGenreId(String gid) {this.genreId = gid;}
    public void getGenreCount(String gc) {this.genreCount = gc;}

    //---getters
    public String getFirstName() {
        return this.firstname;
    }
    public String getLastName() {
        return this.lastname;
    }
    public String getCustomerId() {
        return this.customerId;
    }
    public String getGenreId() {
        return this.genreId;
    }
    public String getGenreCount() {
        return this.genreCount;
    }
}


