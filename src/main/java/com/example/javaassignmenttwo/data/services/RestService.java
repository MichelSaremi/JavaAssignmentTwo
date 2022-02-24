package com.example.javaassignmenttwo.data.services;

import com.example.javaassignmenttwo.model.Customer;

import java.util.ArrayList;

public interface RestService {

    ArrayList<Customer> getAll();

    static ArrayList<String> getFavGenre(int customerid) {
        return null;
    }

}
