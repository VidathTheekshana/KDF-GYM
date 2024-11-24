package com.victorp.service;

import com.victorp.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientService {

    List<Client> findClientByKeyword(String keyword) throws Exception;
    List<Client> getAllClient() throws Exception;
    List<Client> getAllClientInGroup(String nameWorkout) throws Exception;
}
