package com.rastech.customer.service;

import com.rastech.customer.domain.Customer;
import com.rastech.customer.exception.FraudsterException;
import com.rastech.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Ali Shiravand, 10/2/22 9:59 PM
 */
@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public Customer store(Customer customer) throws FraudsterException {
        Customer result = customerRepository.saveAndFlush(customer);
        boolean fraudster = Boolean.TRUE.equals(restTemplate.getForObject(
                "http://FRAUD-SERVICE/api/v1/fraud/{customer-id}",
                Boolean.class,
                customer.getId()));
        if (fraudster)
            throw new FraudsterException("This customer is a fraudster: " + customer);
        return result;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
