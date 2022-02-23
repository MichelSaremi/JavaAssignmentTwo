package com.example.javaassignmenttwo.data.services;

import com.example.javaassignmenttwo.model.Customer;

import java.util.ArrayList;

public interface RestService<Customer> {

    ArrayList<Customer> getAll();

}
