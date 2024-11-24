package com.victorp.repository;

import com.victorp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("select a from Admin a where a.username = :username")
    Admin findByUsername(@Param("username") String username);

    @Query(value = "select * from Admin a where a.username like %:keyword%", nativeQuery = true)
    List<Admin> findAdminByKeyword(@Param("keyword") String keyword);
}
