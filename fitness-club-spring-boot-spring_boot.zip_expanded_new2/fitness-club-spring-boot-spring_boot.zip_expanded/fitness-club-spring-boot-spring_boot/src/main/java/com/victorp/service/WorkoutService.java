package com.victorp.service;

import com.victorp.model.*;

import java.util.List;

public interface WorkoutService {
    void createWorkoutPersonal(WorkoutPersonal workoutPersonal, Workout workout, Client client, User user) throws Exception;

    void createWorkoutGroup(WorkoutGroup workoutGroup, Workout workout) throws Exception;

    void addToWorkoutGroup(WorkoutGroup workoutGroup, Client client, Workout workout, User user) throws Exception;

    void deleteWorkoutPersonal(Long idPersonal) throws Exception;

    void deleteClientFromGroupWorkout(WorkoutGroup workoutGroup, Client client, Workout workout, User user)throws Exception;

    List<WorkoutPersonal> getAllPersonal() throws Exception;

    Workout getByName(String name) throws Exception;

    WorkoutGroup getWorkoutGroupByName(String nameWorkout) throws Exception;

    User getUserByLogin(String login) throws Exception;

}
