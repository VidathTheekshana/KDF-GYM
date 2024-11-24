package com.victorp.repository;


import com.victorp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query(value = "select * from User u where u.username like %:keyword% or u.last_name like %:keyword%", nativeQuery = true)
    List<User> findUserByKeyword(@Param("keyword") String keyword);


}
