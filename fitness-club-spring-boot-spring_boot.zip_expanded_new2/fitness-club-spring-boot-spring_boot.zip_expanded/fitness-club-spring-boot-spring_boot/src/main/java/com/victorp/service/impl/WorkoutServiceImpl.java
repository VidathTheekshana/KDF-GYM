package com.victorp.service.impl;

import com.victorp.model.*;
import com.victorp.repository.*;
import com.victorp.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private WorkoutRepository workoutRepository;

    private WorkoutPersonalRepository workoutPersonalRepository;

    private ClientRepository clientRepository;

    private UserRepository userRepository;

    private WorkoutGroupRepository workoutGroupRepository;

    @Autowired
    public WorkoutServiceImpl(WorkoutRepository workoutRepository, WorkoutPersonalRepository workoutPersonalRepository, ClientRepository clientRepository, UserRepository userRepository, WorkoutGroupRepository workoutGroupRepository) {
        this.workoutRepository = workoutRepository;
        this.workoutPersonalRepository = workoutPersonalRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.workoutGroupRepository = workoutGroupRepository;
    }

    @Override
    public void createWorkoutPersonal(WorkoutPersonal workoutPersonal, Workout workout, Client client, User user) throws Exception {

        workoutPersonalRepository.save(workoutPersonal);

        workoutRepository.save(workout);

        clientRepository.save(client);

        userRepository.save(user);

    }

    public boolean saveWorkoutPersonal(){
        return true;
    }

    @Override
    public void createWorkoutGroup(WorkoutGroup workoutGroup, Workout workout) throws Exception {

        workoutGroupRepository.save(workoutGroup);
        workoutRepository.save(workout);

    }

    @Override
    public void addToWorkoutGroup(WorkoutGroup workoutGroup, Client client, Workout workout, User user) throws Exception {

        workoutGroupRepository.save(workoutGroup);
        clientRepository.save(client);
        userRepository.save(user);
    }

    @Override
    public void deleteWorkoutPersonal(Long idPersonal) throws Exception {
        workoutPersonalRepository.deleteById(idPersonal);
    }

    @Override
    public void deleteClientFromGroupWorkout(WorkoutGroup workoutGroup, Client client, Workout workout, User user) throws Exception {
        workoutGroupRepository.save(workoutGroup);
        clientRepository.save(client);

    }

    @Override
    public List<WorkoutPersonal> getAllPersonal() throws Exception {
        return workoutPersonalRepository.findAll();
    }

    @Override
    public Workout getByName(String name) throws Exception {
        return null;
    }

    @Override
    public WorkoutGroup getWorkoutGroupByName(String nameWorkout) throws Exception {
        return workoutGroupRepository.findByWorkoutName(nameWorkout);
    }

    @Override
    public User getUserByLogin(String login) throws Exception {
        return null;
    }
}
