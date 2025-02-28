package com.farmweb.api.repository;

import com.farmweb.api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByCustomerName(String customerName);


    Customer deleteCustomerByCustomerId(Integer customerId);
}