package com.example.javaassignmenttwo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


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
        SqliteHelper sqliteHelper = new SqliteHelper();
        ArrayList<Customer> customers = sqliteHelper.selectAllCustomers();
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
        SqliteHelper sqliteHelper = new SqliteHelper();
        Customer c = sqliteHelper.selectSpecificCustomerID(id);
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
        SqliteHelper sqliteHelper = new SqliteHelper();
        ArrayList<Customer> customers = sqliteHelper.selectSpecificCustomersName(name);
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
        SqliteHelper sqliteHelper = new SqliteHelper();
        ArrayList<Customer> customers = sqliteHelper.selectSubsetOfCustomers(offset,limit);
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

    //---get a customers favourite genre
    @GetMapping("/Customers/genre/{id}")
    public ArrayList<String> customerFavGenre( @PathVariable("id") String id){
        SqliteHelper sqliteHelper = new SqliteHelper();
        Program program = new Program();
        Genre favGenre1 = null;
        Genre favGenre2 = null;

        //---link customer ID to invoice ID
        ArrayList<CustomerInvoice> customerInvoices = sqliteHelper.selectAllInvoiceByID(id);

        //---Link invoice ID to track ID
        ArrayList<InvoiceLine> UserInvoiceLines = new ArrayList<InvoiceLine>();
        for (CustomerInvoice ci : customerInvoices) {
            String InvoiceID = ci.getInvoiceId();

            ArrayList<InvoiceLine> UserInvoiceLine = sqliteHelper.selectUserInvoiceLines(InvoiceID);
            UserInvoiceLines.addAll(UserInvoiceLine);
        }

        //---link track ID to genre ID
        ArrayList<Track> UserTracks = new ArrayList<Track>();
        for (InvoiceLine uil : UserInvoiceLines) {
            String trackID = uil.getTrackId();


            ArrayList<Track> UserTrack = sqliteHelper.selectTracks(trackID);
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
            favGenre1 = sqliteHelper.selectFavGenre(mapKeys.get(0));
            favGenre2 = sqliteHelper.selectFavGenre(mapKeys.get(1));
            genre.add(favGenre1.getName());
            genre.add(favGenre2.getName());

            //---else display the first genre
        }else if (mapValues.get(0) != mapValues.get(1)){
            favGenre1 = sqliteHelper.selectFavGenre(mapKeys.get(0));
            genre.add(favGenre1.getName());
        }
        return genre;
    }

}