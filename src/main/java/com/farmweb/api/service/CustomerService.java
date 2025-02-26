package com.farmweb.api.service;

import com.farmweb.api.dto.CustomerDTO;
import com.farmweb.api.exception.CustomerException;
import com.farmweb.api.model.Customer;

public interface CustomerService {
   void createCustomer(CustomerDTO customerDTO);
   String login(String customerName, String customerPassword);
   CustomerDTO getCustomerDTOByID(Integer customerID) throws CustomerException;
}
