package com.example.javaassignmenttwo.view;

import com.example.javaassignmenttwo.data.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewCustomerController {
    private final CustomerService customerService;

    public ViewCustomerController(
            CustomerService customerService ) {

        this.customerService = customerService;
    }
    @GetMapping("/list")
    public String view(
            Model model
    ){
        model.addAttribute("customers", customerService.getAll());
        return "customers";
    }
}


