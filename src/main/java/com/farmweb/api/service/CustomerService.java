package com.farmweb.api.service;

import com.farmweb.api.dto.CustomerDTO;
import com.farmweb.api.exception.CustomerException;
import com.farmweb.api.model.Customer;
import com.farmweb.api.model.History;

import java.util.List;

public interface CustomerService {
   void createCustomer(CustomerDTO customerDTO);
   String login(String customerName, String customerPassword);
   CustomerDTO getCustomerDTOByID(Integer customerID) throws CustomerException;
   CustomerDTO deleteCustomerDTOByID(Integer customerID) throws CustomerException;
  // CustomerDTO updateCustomerDTOByID(Integer customerID, CustomerDTO customerDTO) throws CustomerException;
   CustomerDTO updateCustomerName(Integer customerID,String customerName) throws CustomerException;
   CustomerDTO updateCustomerPhoneNumber(Integer customerID,String customerPhoneNumber) throws CustomerException;
   CustomerDTO updateCustomerEmail(Integer customerID,String customerEmail) throws CustomerException;
   CustomerDTO updateCustomerAddress(Integer customerID,String customerAddress) throws CustomerException;
    List<History> getLast10TransactionsByCustomerId(Integer customerId);

}
