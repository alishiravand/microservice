package com.rastech.customer.repository;

import com.rastech.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ali Shiravand, 10/3/22 1:27 PM
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
