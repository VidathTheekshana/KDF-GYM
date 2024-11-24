package com.victorp.service;



import com.victorp.model.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> findAdminByKeyword(String keyword) throws Exception;

    void delete(Long id) throws Exception;

    Admin getByUsername(String username) throws Exception;

    List<Admin> getAll() throws Exception;
}

