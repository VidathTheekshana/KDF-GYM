package com.victorp.service.impl;

import com.victorp.model.Client;
import com.victorp.repository.ClientRepository;
import com.victorp.repository.WorkoutGroupRepository;
import com.victorp.service.ClientService;
import com.victorp.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    private WorkoutService workoutService;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, WorkoutService workoutService) {
        this.clientRepository = clientRepository;
        this.workoutService = workoutService;
    }


    @Override
    public List<Client> getAllClient() throws Exception {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> getAllClientInGroup(String nameWorkout) throws Exception {
        List<Client> clients = new ArrayList<>();
        clients = workoutService.getWorkoutGroupByName(nameWorkout).getClientList();
        return clients;
    }

    @Override
    public List<Client> findClientByKeyword(String keyword) throws Exception {
        return clientRepository.findClientByKeyword(keyword);
    }
}
