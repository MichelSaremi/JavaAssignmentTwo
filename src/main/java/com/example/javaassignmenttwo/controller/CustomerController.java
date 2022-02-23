package com.example.javaassignmenttwo.controller;

import com.example.javaassignmenttwo.*;
import com.example.javaassignmenttwo.data.repository.CustomerRepositoryImpl;
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
    @GetMapping("/Customers/genre/{id}")
    public ArrayList<String> customerFavGenre( @PathVariable("id") String id){
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        Program program = new Program();
        Genre favGenre1 = null;
        Genre favGenre2 = null;

        //---link customer ID to invoice ID
        ArrayList<CustomerInvoice> customerInvoices = customerRepository.selectAllInvoiceByID(id);

        //---Link invoice ID to track ID
        ArrayList<InvoiceLine> UserInvoiceLines = new ArrayList<InvoiceLine>();
        for (CustomerInvoice ci : customerInvoices) {
            String InvoiceID = ci.getInvoiceId();

            ArrayList<InvoiceLine> UserInvoiceLine = customerRepository.selectUserInvoiceLines(InvoiceID);
            UserInvoiceLines.addAll(UserInvoiceLine);
        }

        //---link track ID to genre ID
        ArrayList<Track> UserTracks = new ArrayList<Track>();
        for (InvoiceLine uil : UserInvoiceLines) {
            String trackID = uil.getTrackId();


            ArrayList<Track> UserTrack = customerRepository.selectTracks(trackID);
            UserTracks.addAll(UserTrack);
        }

        //---Sort Track objects according to genreID
        Collections.sort(UserTracks,new Comparator<Track>(){
            public int compare(Track t1, Track t2){
                return t2.getGenreId().compareTo(t1.getGenreId());
            }});

        //---Extract the genre ID for the user and put in new list
        ArrayList<String> genrelist = new ArrayList<String>();
        for (int i = 0; i<UserTracks.size();i++){
            genrelist.add(UserTracks.get(i).getGenreId());
        }

        //---Put each genre ID in a hash-map as key with its frequency as value
        HashMap<String,Integer> genreMap = new HashMap<String,Integer>();
        for(String s: genrelist){
            genreMap.put(s, Collections.frequency(genrelist,s));
        }

        //---Sort hashmap according to value
        Map<String, Integer> newMap = program.sortByValue(genreMap);

        //---extract keys(Genre ID) and values(frequency) into seperate lists
        ArrayList<String> mapKeys = new ArrayList<>();
        ArrayList<String> mapValues = new ArrayList<>();

        newMap.keySet().forEach(key -> mapKeys.add(key));
        newMap.values().forEach(val -> mapValues.add(String.valueOf(val)));

        //---If the first two values are tied
        //---display both genre
        ArrayList<String> genre = new ArrayList<>();

        if (mapValues.get(0) == mapValues.get(1)){
            favGenre1 = customerRepository.selectFavGenre(mapKeys.get(0));
            favGenre2 = customerRepository.selectFavGenre(mapKeys.get(1));
            genre.add(favGenre1.getName());
            genre.add(favGenre2.getName());

            //---else display the first genre
        }else if (mapValues.get(0) != mapValues.get(1)){
            favGenre1 = customerRepository.selectFavGenre(mapKeys.get(0));
            genre.add(favGenre1.getName());
        }
        return genre;
    }

}