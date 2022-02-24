package com.example.javaassignmenttwo.data.repository;

import com.example.javaassignmenttwo.model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RestRepository {
    ArrayList<Customer> selectAllCustomers();
    Customer selectSpecificCustomerID(String customerId);
    ArrayList<Customer>  selectSpecificCustomersName(String customerFirstName);
    ArrayList<Customer> selectSubsetOfCustomers(int offset,int limit);
    ArrayList<CustomerInvoice> selectAllInvoiceByID(int id);
    ArrayList<InvoiceLine> selectUserInvoiceLines(String id);
    ArrayList<Track> selectTracks(String trackID);

    Genre selectSpecificGenre(int genreID);

    Customer addNewCustomer(Customer newCustomer) throws SQLException;
    Customer updateExistingCustomer(Customer existingCustomer) throws SQLException;
    ArrayList<CustomerSpender> orderCustomerByBiggestSpender() throws SQLException;
    ArrayList<CustomerCountry> orderCustomerByCountry() throws SQLException;


}