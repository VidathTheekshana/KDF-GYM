package com.victorp.repository;

import com.victorp.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("select ur from UserRole ur where ur.name = :name")
    UserRole findByName(@Param("name") String name);
}
