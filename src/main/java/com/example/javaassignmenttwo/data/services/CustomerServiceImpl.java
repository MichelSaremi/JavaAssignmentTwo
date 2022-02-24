package com.example.javaassignmenttwo.data.services;

import com.example.javaassignmenttwo.data.repository.CustomerRepositoryImpl;
import com.example.javaassignmenttwo.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepositoryImpl customerRepository;

    public CustomerServiceImpl(
            CustomerRepositoryImpl customerRepository
    ) {
        this.customerRepository = customerRepository;
    }

    public String getCustomerById( @PathVariable("id") String id){
        String output;
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

    public ArrayList<String> getCustomerByName( @PathVariable("name") String name){
        String output = null;
        ArrayList<String> outputs = new ArrayList<>();
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

    public ArrayList<String> getSubsetOfCustomers(@PathVariable("offset") int offset, @PathVariable("limit") int limit){
        String output = null;
        ArrayList<String> outputs = new ArrayList<>();
        //CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
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

    public ArrayList<Customer> getAll() { return customerRepository.selectAllCustomers(); }

    public String addNewCustomer(String firstname, String lastname, String country, String postalCode, String phoneNumber, String email) throws SQLException
    {
        // normally we would get an input from the view, here we're setting the parameters for the new customer!
        Customer newCustomer = new Customer("-1", firstname, lastname, country, postalCode, phoneNumber, email);

        String output = null;
        ArrayList<String> outputs = new ArrayList<String>();
        newCustomer = customerRepository.addNewCustomer(newCustomer);

        output=
                newCustomer.getCustomerFirstname()+", "+
                        newCustomer.getCustomerLastname()+", "+
                        newCustomer.getCustomerCountry()+"," +
                        newCustomer.getCustomerPostalCode()+"," +
                        newCustomer.getCustomerPhoneNumber()+"," +
                        newCustomer.getCustomerEmail()+". ";
        return output;
    }

    public String updateExistingCustomer(String CustomerId, String firstname, String lastname, String country, String postalCode, String phoneNumber, String email) throws SQLException
    {
        String output = null;
        //CustomerRepositoryImpl sqliteHelper = new CustomerRepositoryImpl(null);
        // first get the existing customer by ID, who we wish to update on.
        Customer existingCustomer = new Customer(CustomerId, firstname, lastname, country, postalCode, phoneNumber, email);

        // then we let the update commence.
        existingCustomer = customerRepository.updateExistingCustomer(existingCustomer);

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

    public ArrayList<String> getNumberByCountry() throws SQLException
    {
        String output = null;
        ArrayList<String> outputs = new ArrayList<String>();
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

    public ArrayList<String> getBiggestSpender() throws SQLException
    {
        String output = null;
        ArrayList<String> outputs = new ArrayList<String>();
        //CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl(null);
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

    public ArrayList<String> getFavGenre(int customerid){
        //CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        Genre favGenre1 = null;
        Genre favGenre2 = null;

        //---link customer ID to invoice ID
        ArrayList<CustomerInvoice> customerInvoices = customerRepository.selectAllInvoiceByID(customerid);

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
        Map<String, Integer> newMap = sortByValue(genreMap);

        //---extract keys(Genre ID) and values(frequency) into seperate lists
        ArrayList<String> mapKeys = new ArrayList<>();
        ArrayList<String> mapValues = new ArrayList<>();

        newMap.keySet().forEach(key -> mapKeys.add(key));
        newMap.values().forEach(val -> mapValues.add(String.valueOf(val)));

        //---If the first two values are tied
        //---display both genre
        ArrayList<String> genre = new ArrayList<>();

        if (mapValues.get(0) == mapValues.get(1)){
            favGenre1 = customerRepository.selectSpecificGenre(Integer.parseInt(mapKeys.get(0)));
            favGenre2 = customerRepository.selectSpecificGenre(Integer.parseInt(mapKeys.get(1)));
            genre.add(favGenre1.getName());
            genre.add(favGenre2.getName());

            //---else display the first genre
        }else if (mapValues.get(0) != mapValues.get(1)){
            favGenre1 = customerRepository.selectSpecificGenre(Integer.parseInt(mapKeys.get(0)));
            genre.add(favGenre1.getName());
        }
        return genre;
    }

    //---method to sort a Hashmap by value
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {

        //---Creating a list from elements of HashMap
        List<Map.Entry<String, Integer> > list
                = new LinkedList<Map.Entry<String, Integer> >(
                hm.entrySet());

        //---Sort the list using Collections.sort() method
        //---with Comparator
        Collections.sort(
                list,
                new Comparator<Map.Entry<String, Integer> >() {
                    public int compare(
                            Map.Entry<String, Integer> object1,
                            Map.Entry<String, Integer> object2)
                    {
                        return (object2.getValue())
                                .compareTo(object1.getValue());
                    }
                });

        //---putting sorted data back into hashmap
        HashMap<String, Integer> result
                = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> me : list) {
            result.put(me.getKey(), me.getValue());
        }

        //---returning the sorted HashMap
        return result;
    }

}
