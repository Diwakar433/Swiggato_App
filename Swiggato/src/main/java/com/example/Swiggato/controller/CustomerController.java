package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    // create Constructor Injection ----> Always use in enterprise applications

    final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest) {

        CustomerResponse customerResponse = customerService.adCustomer(customerRequest);

        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find-customer-by-mobile/{mobile}")
    public ResponseEntity getCustomerByMobile(@PathVariable("mobile") String mobile) {
        try{
            CustomerResponse customerResponse = customerService.getCustomerByMobile(mobile);
            return new ResponseEntity(customerResponse, HttpStatus.FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
