package com.farmweb.api.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    private LocalDate transactionDate;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public History(Integer transactionId, LocalDate transactionDate, Double amount, Customer customer, Vendor vendor) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.customer = customer;
        this.vendor = vendor;
    }

    public History() {
    }
}
