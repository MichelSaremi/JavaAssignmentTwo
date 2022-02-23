package com.example.javaassignmenttwo.data.services;

import com.example.javaassignmenttwo.data.repository.CustomerRepositoryImpl;
import com.example.javaassignmenttwo.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerServiceImpl {

    private final CustomerRepositoryImpl customerRepository;

    public CustomerServiceImpl(
            CustomerRepositoryImpl customerRepository
    ) {
        this.customerRepository = customerRepository;
    }


    public ArrayList<Customer> getAll() { return customerRepository.selectAllCustomers(); }


}
