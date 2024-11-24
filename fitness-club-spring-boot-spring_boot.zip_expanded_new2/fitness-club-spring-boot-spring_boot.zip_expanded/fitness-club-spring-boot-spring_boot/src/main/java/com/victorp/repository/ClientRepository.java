package com.victorp.repository;

import com.victorp.model.Client;
import com.victorp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "select * from Client c where c.username like %:keyword%", nativeQuery = true)
    List<Client> findClientByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM client WHERE username = :username", nativeQuery = true)
    Client findClientByUsername(@Param("username") String username);
}
