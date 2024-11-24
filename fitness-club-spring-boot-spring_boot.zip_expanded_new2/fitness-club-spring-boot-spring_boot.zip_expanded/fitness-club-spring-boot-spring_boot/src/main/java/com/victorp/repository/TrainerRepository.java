package com.victorp.repository;


import com.victorp.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    @Query("select t from Trainer t where t.username = :username")
    Trainer findByUsername(@Param("username") String username);

    @Query(value = "select * from Trainer t where t.username like %:keyword% or t.name_group like %:keyword% ", nativeQuery = true)
    List<Trainer> findTrainerByKeyword(@Param("keyword") String keyword);
}
