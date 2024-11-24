package com.victorp.service.impl;

import com.victorp.model.Trainer;
import com.victorp.repository.TrainerRepository;
import com.victorp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public List<Trainer> findTrainerByKeyword(String keyword) throws Exception {
        return trainerRepository.findTrainerByKeyword(keyword);
    }

    @Override
    public void delete(Long id) throws Exception {
        trainerRepository.deleteById(id);
    }

    @Override
    public Trainer getByUsername(String username) throws Exception {
        return trainerRepository.findByUsername(username);
    }

    @Override
    public List<Trainer> getAll() throws Exception {
        return trainerRepository.findAll();
    }
}
