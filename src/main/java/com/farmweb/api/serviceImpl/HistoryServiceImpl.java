package com.farmweb.api.serviceImpl;

import com.farmweb.api.dto.CustomerDTO;
import com.farmweb.api.dto.HistoryDTO;
import com.farmweb.api.exception.CustomerException;
import com.farmweb.api.model.Customer;
import com.farmweb.api.model.History;
import com.farmweb.api.model.Vendor;
import com.farmweb.api.repository.CustomerRepository;
import com.farmweb.api.repository.HistoryRepository;
import com.farmweb.api.repository.VendorRepository;
import com.farmweb.api.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VendorRepository vendorRepository;


    @Override
    public History createHistoryEntry(HistoryDTO historyDTO) {

        return null;

    }
}
