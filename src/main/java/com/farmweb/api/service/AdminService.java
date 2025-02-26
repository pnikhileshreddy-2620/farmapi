package com.farmweb.api.service;

import com.farmweb.api.dto.AdminDTO;
import com.farmweb.api.model.Admin;

public interface AdminService {

    void createAdmin(AdminDTO adminDTO);
    String loginAdmin(AdminDTO adminDTO);

}
