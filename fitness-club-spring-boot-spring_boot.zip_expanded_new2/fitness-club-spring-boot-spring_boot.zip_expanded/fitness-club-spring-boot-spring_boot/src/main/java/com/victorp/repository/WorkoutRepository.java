package com.victorp.repository;

import com.victorp.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface WorkoutRepository extends JpaRepository<Workout, Long> {



}
