package com.victorp.repository;

import com.victorp.model.User;
import com.victorp.model.WorkoutGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface WorkoutGroupRepository extends JpaRepository<WorkoutGroup, Long> {
    @Query(value = "SELECT * FROM workout_group  WHERE name_workout = :nameWorkout", nativeQuery = true)
    WorkoutGroup findByWorkoutName(@Param("nameWorkout") String nameWorkout);
}
