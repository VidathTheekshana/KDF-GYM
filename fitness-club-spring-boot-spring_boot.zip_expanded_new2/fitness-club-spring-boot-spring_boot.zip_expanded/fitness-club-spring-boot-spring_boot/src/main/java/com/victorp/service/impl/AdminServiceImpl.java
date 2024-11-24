package com.victorp.service.impl;

import com.victorp.model.Admin;
import com.victorp.repository.AdminRepository;
import com.victorp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Admin> findAdminByKeyword(String keyword) throws Exception {
        return adminRepository.findAdminByKeyword(keyword);
    }

    @Override
    public void delete(Long id) throws Exception {

        adminRepository.deleteById(id);
    }

    @Override
    public Admin getByUsername(String username) throws Exception {
        return adminRepository.findByUsername(username);
    }

    @Override
    public List<Admin> getAll() throws Exception {
        return adminRepository.findAll();
    }
}
