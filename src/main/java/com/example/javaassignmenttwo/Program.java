package com.example.javaassignmenttwo;

import com.example.javaassignmenttwo.data.CustomerRepository;
import com.example.javaassignmenttwo.model.*;

import java.sql.SQLException;
import java.util.*;

public class Program {

    public static void main(String[] args) throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        //ArrayList<Customer> customers = customerRepository.selectAllCustomers();
        //ArrayList<Customer> customers = customerRepository.selectSpecificCustomersName("Mark");
        //ArrayList<Customer> customers = customerRepository.selectSubsetOfCustomers(12,4);
        //Customer customer = customerRepository.selectSpecificCustomerID("55");
        //printCustomers(customers);
        //printCustomer(customer);


        mostPopularGenre("1");

        /**
         * Added function calls to task 5, 6, 7 and 8 here. I needed to nest these calls inside a try catch statement to check the SQL setup and query were correct.
         */


        //customerRepository.addNewCustomer(null); // task 5
        //customerRepository.updateExistingCustomer(null); // task 6
        //customerRepository.orderCustomerByCountry(); // task 7
        //customerRepository.orderCustomerByBiggestSpender(); // task 8


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

    //---print more than one Customer objects
    public static void printCustomers(ArrayList<Customer> customers) {
        if(customers.size() != 0) {
            for (Customer c : customers) {
                System.out.println("-------------------------------");
                System.out.println(c.getCustomerId());
                System.out.println(c.getCustomerFirstname());
                System.out.println(c.getCustomerLastname());
                System.out.println(c.getCustomerCountry());
                System.out.println(c.getCustomerPostalCode());
                System.out.println(c.getCustomerPhoneNumber());
                System.out.println(c.getCustomerEmail());
            }
        } else {
            System.out.println("No customers returned");
        }
    }

    //---print customer object
    public static void printCustomer(Customer customer) {
        if(customer != null){
            System.out.println("-------------------------------");
            System.out.println(customer.getCustomerId());
            System.out.println(customer.getCustomerFirstname());
            System.out.println(customer.getCustomerLastname());
            System.out.println(customer.getCustomerCountry());
            System.out.println(customer.getCustomerPostalCode());
            System.out.println(customer.getCustomerPhoneNumber());
            System.out.println(customer.getCustomerEmail());
        } else {
            System.out.println("No customer with that ID exists");
        }

    }

    //---print CustomerInvoice objects
    public static void printInvoices(ArrayList<CustomerInvoice> customerInvoices){
        if(customerInvoices.size() != 0) {
            for (CustomerInvoice i : customerInvoices) {
            System.out.println("-------------------------------");
            System.out.println("Customer ID "+i.getCustomerId());
            System.out.println("Invoice ID "+i.getInvoiceId());
    }}}

    //---print InvoiceLine objects
    public static void printInvoicelines(ArrayList<InvoiceLine> UserInvoiceLines){
        if(UserInvoiceLines.size() != 0) {
            for (InvoiceLine il : UserInvoiceLines) {
                System.out.println("-------------------------------");
                System.out.println("Invoice ID "+il.getInvoiceId());
                System.out.println("Track ID "+il.getTrackId());
            }}}

    //---print track objects
    public static void printTracks(ArrayList<Track> Tracks){
        if(Tracks.size() != 0) {
            for (Track t : Tracks) {
                System.out.println("-------------------------------");
                System.out.println("Track ID "+t.getTrackId());
                System.out.println("Genre ID "+t.getGenreId());
            }}}

    //---print genre objects
    public static void printGenre(Genre genre) {
        if (genre != null) {
            System.out.println("-------------------------------");
            System.out.println("Genre ID : " + genre.getGenreId());
            System.out.println("Name : " + genre.getName());
        } else {
            System.out.println("No genre");
        }
    }

    //---Display users most popular genre
    public static void mostPopularGenre(String id ){

        CustomerRepository customerRepository = new CustomerRepository();

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
        printTracks(UserTracks);

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
        System.out.println(newMap);

        //---extract keys(Genre ID) and values(frequency) into seperate lists
        ArrayList<String> mapKeys = new ArrayList<>();
        ArrayList<String> mapValues = new ArrayList<>();

        newMap.keySet().forEach(key -> mapKeys.add(key));
        newMap.values().forEach(val -> mapValues.add(String.valueOf(val)));

        System.out.println(mapKeys);
        System.out.println(mapValues);

        System.out.println("first value "+mapValues.get(0));
        System.out.println("second value "+mapValues.get(1));

        //---If the first two values are tied
        //---display both genre
        if (mapValues.get(0) == mapValues.get(1)){
            Genre favGenre1 = customerRepository.selectFavGenre(mapKeys.get(0));
            Genre favGenre2 = customerRepository.selectFavGenre(mapKeys.get(1));
            System.out.println("Users most favourite genre");
            printGenre(favGenre1);
            printGenre(favGenre2);

        //---else display the first genre
        }else if (mapValues.get(0) != mapValues.get(1)){
            Genre favGenre1 = customerRepository.selectFavGenre(mapKeys.get(0));
            System.out.println("Users most favourite genre");
            printGenre(favGenre1);
        }
    }
}