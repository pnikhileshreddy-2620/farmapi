package com.farmweb.api.serviceImpl;

import com.farmweb.api.dto.VendorDTO;
import com.farmweb.api.exception.CustomerException;
import com.farmweb.api.exception.VendorException;
import com.farmweb.api.model.Vendor;
import com.farmweb.api.repository.VendorRepository;
import com.farmweb.api.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        if (vendor != null&& passwordEncoder.matches(VendorDTO.getVendorPassword(), vendor.getVendorPassword())) {
            return "Vendor  Found";
        }
        return "vendor not found";
    }

    @Override
    public VendorDTO getVendorDTOById(Integer VendorID) {
        VendorDTO vendorDTO = new VendorDTO();
        Vendor vendor = vendorRepository.findById(VendorID).orElseThrow(()->new VendorException("Vendor not found"+VendorID));
        vendorDTO.setVendorId(vendor.getVendorId());
        vendorDTO.setVendorName(vendor.getVendorName());
        vendorDTO.setVendorEmail(vendor.getVendorEmail());
        vendorDTO.setVendorPhone(vendor.getVendorPhone());
        return vendorDTO;
    }
    @Override
    public VendorDTO deleteVendor(Integer vendorID) throws VendorException {
        VendorDTO vendorDTO = getVendorDTOById(vendorID);
        if (vendorDTO == null) {
            throw new VendorException("Vendor with ID " + vendorID + " not found");
        }
        System.out.println(2);
        vendorRepository.deleteById(vendorID);
        return vendorDTO;
    }

    @Override
    public VendorDTO updateVendorName(Integer vendorID, String VendorName) throws VendorException {
        Optional<Vendor> optionalVendor = vendorRepository.findById(vendorID);
        if (optionalVendor.isEmpty()) {
            throw new VendorException("Vendor with id " + vendorID + " not found");
        }
        Vendor vendor = optionalVendor.get();

        if(vendor==null)
            throw new VendorException("Vendor with ID " + vendorID + " not found");
        vendor.setVendorName(VendorName);
        vendorRepository.save(vendor);
        return new VendorDTO(vendor);
    }

//    @Override
//    public VendorDTO updateVendorEmail(Integer VendorID, String VendorEmail) throws VendorException {
//        Vendor vendor =  vendorRepository.getById(VendorID);
//        if(vendor==null)
//            throw new VendorException("Vendor with ID " + VendorID + " not found");
//        vendor.setVendorEmail(VendorEmail);
//        vendorRepository.save(vendor);
//        return new VendorDTO(vendor);
//    }
//
//    @Override
//    public VendorDTO updateVendorPhone(Integer VendorID, String VendorPhone) throws VendorException {
//        Vendor vendor =  vendorRepository.getById(VendorID);
//        if(vendor==null)
//            throw new VendorException("Vendor with ID " + VendorID + " not found");
//        vendor.setVendorPhone(VendorPhone);
//        vendorRepository.save(vendor);
//        return new VendorDTO(vendor);
//    }
//
//    @Override
//    public VendorDTO updateVendorAddress(Integer VendorID, String VendorAddress) throws VendorException {
//        Vendor vendor =  vendorRepository.getById(VendorID);
//        if(vendor==null)
//            throw new VendorException("Vendor with ID " + VendorID + " not found");
//        vendor.setVendorAddress(VendorAddress);
//        vendorRepository.save(vendor);
//        return new VendorDTO(vendor);
//    }


}
