package com.example.javaassignmenttwo.controller;

import com.example.javaassignmenttwo.*;
import com.example.javaassignmenttwo.data.repository.CustomerRepositoryImpl;
import com.example.javaassignmenttwo.data.services.CustomerServiceImpl;
import com.example.javaassignmenttwo.model.*;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.*;

@RestController
//@Tag(name = "All customers")
public class CustomerController {

    //@Operation(summary =
    //---get all customers
    @GetMapping("/Customers")
    public ArrayList<String> allCustomers(){
        String output = null;
        ArrayList<String> outputs = new ArrayList<>();
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        ArrayList<Customer> customers = customerRepository.selectAllCustomers();
        if(customers.size() != 0) {
            for (Customer c : customers) {
                output=
                        c.getCustomerId()+", "+
                                c.getCustomerFirstname()+", "+
                                c.getCustomerLastname()+", "+
                                c.getCustomerCountry()+", "+
                                c.getCustomerPostalCode()+", "+
                                c.getCustomerPhoneNumber()+", "+
                                c.getCustomerEmail()+". ";
                outputs.add(output);
            }}
        return outputs;
    }

    //---get a specific customer based on ID
    @GetMapping("/Customers/id/{id}")
    public String customerById( @PathVariable("id") String id){
        String output;
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        Customer c = customerRepository.selectSpecificCustomerID(id);
        output=
                c.getCustomerId()+", "+
                        c.getCustomerFirstname()+", "+
                        c.getCustomerLastname()+", "+
                        c.getCustomerCountry()+", "+
                        c.getCustomerPostalCode()+", "+
                        c.getCustomerPhoneNumber()+", "+
                        c.getCustomerEmail()+". ";
        return output;
    }

    //---get a specific customer based on name
    @GetMapping("/Customers/name/{name}")
    public ArrayList<String> customerByName( @PathVariable("name") String name){
        String output = null;
        ArrayList<String> outputs = new ArrayList<>();
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        ArrayList<Customer> customers = customerRepository.selectSpecificCustomersName(name);
        if(customers.size() != 0) {
            for (Customer c : customers) {
                output=
                        c.getCustomerId()+", "+
                                c.getCustomerFirstname()+", "+
                                c.getCustomerLastname()+", "+
                                c.getCustomerCountry()+", "+
                                c.getCustomerPostalCode()+", "+
                                c.getCustomerPhoneNumber()+", "+
                                c.getCustomerEmail()+". ";
                outputs.add(output);
            }}
        return outputs;
    }

    //---get a subset of customers
    @GetMapping("/Customers/subset/{offset}/{limit}")
    public ArrayList<String> customerByName( @PathVariable("offset") int offset, @PathVariable("limit") int limit){
        String output = null;
        ArrayList<String> outputs = new ArrayList<>();
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        ArrayList<Customer> customers = customerRepository.selectSubsetOfCustomers(offset,limit);
        if(customers.size() != 0) {
            for (Customer c : customers) {
                output=
                        c.getCustomerId()+", "+
                                c.getCustomerFirstname()+", "+
                                c.getCustomerLastname()+", "+
                                c.getCustomerCountry()+", "+
                                c.getCustomerPostalCode()+", "+
                                c.getCustomerPhoneNumber()+", "+
                                c.getCustomerEmail()+". ";
                outputs.add(output);
            }}
        return outputs;
    }

    //-- get ordered customers by the customers, who have spent the most on the music site.
    //-- get ordered customers by the customers, who have spent the most on the music site.
    @PostMapping("Customers/add")
    public String customerAddNew(@RequestBody Customer customer, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String country, @RequestParam String postalCode, @RequestParam String phoneNumber, @RequestParam String email) throws SQLException
    {
        // normally we would get an input from the view, here we're setting the parameters for the new customer!
        Customer newCustomer = new Customer("-1", firstname, lastname, country, postalCode, phoneNumber, email);

        String output = null;
        ArrayList<String> outputs = new ArrayList<String>();
        CustomerRepositoryImpl sqliteHelper = new CustomerRepositoryImpl();
        newCustomer = sqliteHelper.addNewCustomer(newCustomer);

        output=
                newCustomer.getCustomerFirstname()+", "+
                        newCustomer.getCustomerLastname()+", "+
                        newCustomer.getCustomerCountry()+"," +
                        newCustomer.getCustomerPostalCode()+"," +
                        newCustomer.getCustomerPhoneNumber()+"," +
                        newCustomer.getCustomerEmail()+". ";
        return output;
    }

    @PutMapping("Customers/update/{CustomerId}")
    public String customerUpdateExisting(@RequestParam("CustomerId") String CustomerId, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String country, @RequestParam String postalCode, @RequestParam String phoneNumber, @RequestParam String email, @RequestBody Customer customer) throws SQLException
    {
        String output = null;
        CustomerRepositoryImpl sqliteHelper = new CustomerRepositoryImpl();
        // first get the existing customer by ID, who we wish to update on.
        Customer existingCustomer = new Customer(CustomerId, firstname, lastname, country, postalCode, phoneNumber, email);

        // then we let the update commence.
        existingCustomer = sqliteHelper.updateExistingCustomer(existingCustomer);

        // write out the result of the update
        output=
                existingCustomer.getCustomerFirstname()+", "+
                        existingCustomer.getCustomerLastname()+", "+
                        existingCustomer.getCustomerCountry()+"," +
                        existingCustomer.getCustomerPostalCode()+"," +
                        existingCustomer.getCustomerPhoneNumber()+"," +
                        existingCustomer.getCustomerEmail()+". ";

        return output;
    }

    //-- get ordered customers by the customers, who have spent the most on the music site.
    @GetMapping("/Customers/totalSpent")
    public ArrayList<String> customerBigSpender() throws SQLException
    {
        String output = null;
        ArrayList<String> outputs = new ArrayList<String>();
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        ArrayList<CustomerSpender> customers = customerRepository.orderCustomerByBiggestSpender();

        if(customers.size() != 0) {
            for (CustomerSpender c : customers) {
                output=
                        c.getFirstname()+", "+
                                c.getLastname()+", "+
                                c.getTotalSpent()+". ";
                outputs.add(output);
            }}
        return outputs;
    }

    //-- get ordered customers by the customers, who have spent the most on the music site.
    @GetMapping("/Customers/countries")
    public ArrayList<String> customerNumberByCountry() throws SQLException
    {
        String output = null;
        ArrayList<String> outputs = new ArrayList<String>();
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        ArrayList<CustomerCountry> customers = customerRepository.orderCustomerByCountry();

        if(customers.size() != 0) {
            for (CustomerCountry c : customers) {
                output=
                                c.getCountry()+", "+
                                c.getTotalNumberOfCustomer()+". ";
                outputs.add(output);
            }}
        return outputs;
    }

    //---get a customers favourite genre
    @GetMapping("/Customers/favgenre/{id}")
    public ArrayList<String> customerFavGenre( @PathVariable("id") int cid){
        ArrayList<String> favGenre = CustomerServiceImpl.getFavGenre(cid);
        return favGenre;
    }
}