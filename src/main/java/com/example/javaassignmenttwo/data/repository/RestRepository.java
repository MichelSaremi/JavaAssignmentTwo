package com.example.javaassignmenttwo.data.repository;

import com.example.javaassignmenttwo.model.*;

import java.util.ArrayList;
import java.util.Collection;

public interface RestRepository<T> {
    ArrayList<Customer> selectAllCustomers();
    Customer selectSpecificCustomerID(String customerId);
    ArrayList<Customer>  selectSpecificCustomersName(String customerFirstName);
    ArrayList<Customer> selectSubsetOfCustomers(int offset,int limit);
    ArrayList<CustomerInvoice> selectAllInvoiceByID(String id);
    ArrayList<InvoiceLine> selectUserInvoiceLines(String id);
    ArrayList<Track> selectTracks(String trackID);
    Genre selectFavGenre(String genreID);
    Customer addNewCustomer(Customer newCustomer);
    Customer updateExistingCustomer(Customer existingCustomer);
    ArrayList<CustomerSpender> orderCustomerByBiggestSpender();
    ArrayList<CustomerCountry> orderCustomerByCountry();


}