package com.farmweb.api.serviceImpl;

import com.farmweb.api.dto.VendorDTO;
import com.farmweb.api.exception.VendorException;
import com.farmweb.api.model.Customer;
import com.farmweb.api.model.History;
import com.farmweb.api.model.Vendor;
import com.farmweb.api.repository.CustomerRepository;
import com.farmweb.api.repository.HistoryRepository;
import com.farmweb.api.repository.VendorRepository;
import com.farmweb.api.service.VendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private static final Logger logger = LoggerFactory.getLogger(VendorServiceImpl.class);

    @Override
    public void createVendor(VendorDTO VendorDTO) {
        Vendor vendor = new Vendor();
        vendor.setVendorName(VendorDTO.getVendorName());
        vendor.setVendorAddress(VendorDTO.getVendorAddress());
        vendor.setVendorEmail(VendorDTO.getVendorEmail());
        vendor.setVendorPhone(VendorDTO.getVendorPhone());
        vendor.setVendorPassword(passwordEncoder.encode(VendorDTO.getVendorPassword()));
        vendorRepository.save(vendor);
    }

    @Override
    public String vendorLogin(VendorDTO VendorDTO) {
        Vendor vendor = vendorRepository.findByVendorName(VendorDTO.getVendorName());
        if (vendor != null && passwordEncoder.matches(VendorDTO.getVendorPassword(), vendor.getVendorPassword())) {
            return "Vendor Found";
        }
        return "Vendor not found";
    }

    @Override
    public VendorDTO getVendorDTOById(Integer VendorID) {
        Vendor vendor = vendorRepository.findById(VendorID)
                .orElseThrow(() -> new VendorException("Vendor not found with ID " + VendorID));
        return new VendorDTO(vendor);
    }

    @Override
    public VendorDTO deleteVendor(Integer vendorID) throws VendorException {
        VendorDTO vendorDTO = getVendorDTOById(vendorID);
        vendorRepository.deleteById(vendorID);
        return vendorDTO;
    }

    @Override
    public VendorDTO updateVendorName(Integer vendorID, String VendorName) throws VendorException {
        Vendor vendor = vendorRepository.findById(vendorID)
                .orElseThrow(() -> new VendorException("Vendor not found with ID " + vendorID));
        vendor.setVendorName(VendorName);
        vendorRepository.save(vendor);
        return new VendorDTO(vendor);
    }

    @Override
    public VendorDTO updateVendorEmail(Integer VendorID, String VendorEmail) throws VendorException {
        Vendor vendor = vendorRepository.findById(VendorID)
                .orElseThrow(() -> new VendorException("Vendor not found with ID " + VendorID));
        vendor.setVendorEmail(VendorEmail);
        vendorRepository.save(vendor);
        return new VendorDTO(vendor);
    }

    @Override
    public VendorDTO updateVendorPhone(Integer vendorID, String vendorPhone) throws VendorException {
        Vendor vendor = vendorRepository.findById(vendorID)
                .orElseThrow(() -> new VendorException("Vendor not found with ID " + vendorID));
        vendor.setVendorPhone(vendorPhone);
        vendorRepository.save(vendor);
        return new VendorDTO(vendor);
    }

    @Override
    public VendorDTO updateVendorData(Integer vendorID, String vendorAddress) throws VendorException {
        Vendor vendor = vendorRepository.findById(vendorID)
                .orElseThrow(() -> new VendorException("Vendor not found with ID " + vendorID));
        vendor.setVendorAddress(vendorAddress);
        vendorRepository.save(vendor);
        return new VendorDTO(vendor);
    }

    @Override
    public void recordTransaction(Integer vendorId, Integer customerId, Integer quantityInMl) {
        logger.info("Recording transaction for Vendor ID: {}, Customer ID: {}, Quantity: {}ml", vendorId, customerId, quantityInMl);

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new VendorException("Vendor not found with ID " + vendorId));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new VendorException("Customer not found with ID " + customerId));

        // Price Calculation: 1 liter (1000ml) = ₹70
        double pricePerMl = 70.0 / 1000;
        double calculatedAmount = quantityInMl * pricePerMl;

        History transaction = new History();
        transaction.setVendor(vendor);
        transaction.setCustomer(customer);
        transaction.setQuantity(quantityInMl);
        transaction.setAmount(calculatedAmount);
        transaction.setTransactionDate(LocalDateTime.now());

        historyRepository.save(transaction);

        logger.info("Transaction saved successfully! Amount: ₹{}", calculatedAmount);
    }


}
