package com.farmweb.api.service;

import com.farmweb.api.dto.VendorDTO;
import com.farmweb.api.exception.VendorException;
import com.farmweb.api.model.Vendor;

public interface VendorService {

    void createVendor(VendorDTO VendorDTO);

    String vendorLogin(VendorDTO VendorDTO);

    VendorDTO  getVendorDTOById(Integer VendorID) throws VendorException;
    VendorDTO  deleteVendor(Integer VendorID) throws VendorException;
    VendorDTO updateVendorName(Integer VendorID, String VendorName) throws VendorException;
//    VendorDTO updateVendorEmail(Integer VendorID, String VendorEmail) throws VendorException;
//    VendorDTO updateVendorPhone(Integer VendorID, String VendorPhone) throws VendorException;
//    VendorDTO updateVendorAddress(Integer VendorID, String VendorAddress) throws VendorException;
}
