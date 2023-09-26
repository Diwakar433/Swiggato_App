package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;
import com.example.Swiggato.model.Cart;
import com.example.Swiggato.model.Customer;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse adCustomer(CustomerRequest customerRequest) {
        // set and save the customer from customerRequest
        Customer saveCustomer = customerRepository.save(CustomerTransformer.CustomerRequestToCustomer(customerRequest));

        Cart cart = Cart.builder()
                .customer(saveCustomer)
                .cartTotal(0)
                .build();

        saveCustomer.setCart(cart);

        // set data to customerResponse

        return CustomerTransformer.CustomerToCustomerResponse(saveCustomer);
    }

    public CustomerResponse getCustomerByMobile(String mobile) {
        Customer customer = customerRepository.findByMobileNo(mobile);

        if(customer == null) {
            throw new CustomerNotFoundException("Invalid mobileNo!!");
        }
        // set data to customerResponse

        return CustomerTransformer.CustomerToCustomerResponse(customer);
    }
}
