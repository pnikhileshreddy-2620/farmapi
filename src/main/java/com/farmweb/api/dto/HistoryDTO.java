package com.farmweb.api.dto;

public class HistoryDTO {

    private Integer vendorId;
    private Integer customerId;
    private Integer amount;
    private Integer quantity;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }


    public HistoryDTO(Integer vendorId, Integer customerId, Integer amount, Integer quantity) {
        this.vendorId = vendorId;
        this.customerId = customerId;
        this.amount = amount;
        this.quantity = quantity;
    }
}
