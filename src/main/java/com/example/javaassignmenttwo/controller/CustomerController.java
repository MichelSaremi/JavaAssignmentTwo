package com.example.javaassignmenttwo.controller;

import com.example.javaassignmenttwo.data.services.CustomerServiceImpl;
import com.example.javaassignmenttwo.model.*;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.*;

@RestController
public class CustomerController {

    private final CustomerServiceImpl customerService;

    //---constructor
    public CustomerController(CustomerServiceImpl customerService)
    {
        this.customerService = customerService;
    }

    //---URL mappings
    //---getting all customers
    @GetMapping("/Customers")
    public ArrayList<Customer> allCustomers(){
        return customerService.getAll();
    }

    //---get a specific customer based on ID
    @GetMapping("/Customers/id/{custemorId}")
    public String customerById( @PathVariable("custemorId") String id){
        return customerService.getCustomerById(id);
    }

    //---get a specific customer based on name
    @GetMapping("/Customers/name/{customerName}")
    public ArrayList<String> customerByName( @PathVariable("customerName") String name){
        return customerService.getCustomerByName(name);
    }

    //---get a subset of customers
    @GetMapping("/Customers/subset/{offset}/{limit}")
    public ArrayList<String> customerSubset( @PathVariable("offset") int offset, @PathVariable("limit") int limit){
        return customerService.getSubsetOfCustomers(offset, limit);
    }

    //-- get ordered customers based on who have spent the most on the music site.
    @PostMapping("Customers/add")
    public String customerAddNew(@RequestBody Customer customer, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String country, @RequestParam String postalCode, @RequestParam String phoneNumber, @RequestParam String email) throws SQLException
    {
        return customerService.addNewCustomer(firstname, lastname, country, postalCode, phoneNumber, email);
    }

    //---update customer
    @PutMapping("Customers/update/{CustomerId}")
    public String customerUpdateExisting(@RequestParam("CustomerId") String CustomerId, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String country, @RequestParam String postalCode, @RequestParam String phoneNumber, @RequestParam String email, @RequestBody Customer customer) throws SQLException
    {
        return customerService.updateExistingCustomer(CustomerId, firstname, lastname, country, postalCode, phoneNumber, email);
    }

    //-- get ordered customers based on who spent the most on the music site.
    @GetMapping("/Customers/totalSpent")
    public ArrayList<String> customerBigSpender() throws SQLException
    {
        return customerService.getBiggestSpender();
    }

    //-- get ordered list of coutomer-count by countries
    @GetMapping("/Customers/countries")
    public ArrayList<String> customerNumberByCountry() throws SQLException
    {
        return customerService.getNumberByCountry();
    }

    //---get a customers favourite genre
    @GetMapping("/Customers/favgenre/{customerId}")
    public ArrayList<String> customerFavGenre( @PathVariable("customerId") int cid){
        ArrayList<String> favGenre = customerService.getFavGenre(cid);
        return favGenre;
    }
}