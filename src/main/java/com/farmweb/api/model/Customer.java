package com.farmweb.api.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
    private String customerPassword;

    @OneToMany(mappedBy = "customer")
    private List<History> transactions;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public List<History> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<History> transactions) {
        this.transactions = transactions;
    }

    public Customer(Integer customerId, String customerName, String customerEmail, String customerPhone, String customerAddress, String customerPassword, List<History> transactions) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.customerPassword = customerPassword;
        this.transactions = transactions;
    }

    public Customer() {
    }

    public void setActive(boolean b) {
    }
}
