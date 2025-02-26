package com.farmweb.api.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vendorId;

    private String vendorName;
    private String vendorAddress;
    private String vendorEmail;
    private String vendorPhone;
    private String vendorPassword;

    @OneToMany(mappedBy = "vendor")
    private List<History> transactions;

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getVendorPassword() {
        return vendorPassword;
    }

    public void setVendorPassword(String vendorPassword) {
        this.vendorPassword = vendorPassword;
    }

    public List<History> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<History> transactions) {
        this.transactions = transactions;
    }

    public Vendor() {
    }

    public Vendor(Integer vendorId, String vendorName, String vendorAddress, String vendorEmail, String vendorPhone, String vendorPassword, List<History> transactions) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorEmail = vendorEmail;
        this.vendorPhone = vendorPhone;
        this.vendorPassword = vendorPassword;
        this.transactions = transactions;
    }
}
