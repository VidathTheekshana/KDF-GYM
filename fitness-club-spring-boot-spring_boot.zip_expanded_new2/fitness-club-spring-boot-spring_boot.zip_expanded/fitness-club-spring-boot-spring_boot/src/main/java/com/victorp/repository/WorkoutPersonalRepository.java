package com.victorp.repository;

import com.victorp.model.Client;
import com.victorp.model.WorkoutPersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface WorkoutPersonalRepository extends JpaRepository<WorkoutPersonal, Long> {

    @Query(value = "select * from WorkoutPersonal c where c.username like %:keyword%", nativeQuery = true)
    List<WorkoutPersonal> findPersonalByKeyword(@Param("keyword") String keyword);
}
