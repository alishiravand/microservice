package com.rastech.customer.service;

import com.rastech.customer.domain.Customer;
import com.rastech.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ali Shiravand, 10/2/22 9:59 PM
 */
@Service
public record CustomerService(CustomerRepository customerRepository) {

    public Customer store(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
