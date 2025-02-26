package com.farmweb.api.service;

import com.farmweb.api.dto.VendorDTO;
import com.farmweb.api.exception.VendorException;
import com.farmweb.api.model.Vendor;

public interface VendorService {

    void createVendor(VendorDTO VendorDTO);

    String vendorLogin(VendorDTO VendorDTO);

    VendorDTO  getVendorDTOById(Integer VendorID) throws VendorException;
}
