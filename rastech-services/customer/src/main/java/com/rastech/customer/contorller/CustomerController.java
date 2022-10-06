package com.rastech.customer.contorller;

import com.rastech.customer.domain.Customer;
import com.rastech.customer.exception.FraudsterException;
import com.rastech.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ali Shiravand, 10/2/22 9:51 PM
 */
@RestController
@RequestMapping("api/v1/customer")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public ResponseEntity<Customer> register(@RequestBody Customer customer) throws FraudsterException {
        return ResponseEntity.ok(customerService.store(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

}
