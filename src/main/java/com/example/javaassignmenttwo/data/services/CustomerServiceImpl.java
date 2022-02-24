package com.example.javaassignmenttwo.data.services;

import com.example.javaassignmenttwo.data.repository.CustomerRepositoryImpl;
import com.example.javaassignmenttwo.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepositoryImpl customerRepository;

    public CustomerServiceImpl(
            CustomerRepositoryImpl customerRepository
    ) {
        this.customerRepository = customerRepository;
    }


    public ArrayList<Customer> getAll() { return customerRepository.selectAllCustomers(); }

    public static ArrayList<String> getFavGenre(int customerid){
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
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
