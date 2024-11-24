package com.victorp.service.impl;

import com.victorp.model.*;
import com.victorp.repository.ClientRepository;
import com.victorp.repository.UserRepository;
import com.victorp.repository.UserRoleRepository;
import com.victorp.repository.WorkoutRepository;
import com.victorp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;

    private ClientRepository clientRepository;

    private WorkoutRepository workoutRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ClientRepository clientRepository, WorkoutRepository workoutRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.clientRepository = clientRepository;
        this.workoutRepository = workoutRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User getById(Long id) throws Exception {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) throws Exception {
        return userRepository.findByUsername(username);
    }

    @Override
    public User checkUser(String username) {
        return null;
    }

    @Override
    public User create(User item) throws Exception {
        return userRepository.saveAndFlush(item);
    }
    @Override
    public boolean saveClient(User user) throws Exception{
        User userFromDB = userRepository.findByUsername(user.getUsername());
        UserRole userRoleFromDB = userRoleRepository.findByName("ROLE_CLIENT");
        final Client client = new Client();

        if (userFromDB != null) {
            return false;
        }
        if(userRoleFromDB != null){
            user.addUserRole(userRoleFromDB);
        }else{
            user.addUserRole(new UserRole("ROLE_CLIENT", false, false));
        }

        client.setUser(user);
        client.setUsername(user.getUsername());
        client.setClientIdentifier((long) (Math.random() * 20000 - 0));

        user.setClient(client);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public boolean saveAdmin(User user) throws Exception {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        UserRole userRoleFromDB = userRoleRepository.findByName("ROLE_ADMIN");
        final Admin admin = new Admin();

        if (userFromDB != null) {
            return false;
        }
        if(userRoleFromDB != null){
            user.addUserRole(userRoleFromDB);
        }else{
            user.addUserRole(new UserRole("ROLE_ADMIN", true, false));
        }

        admin.setUser(user);
        admin.setUsername(user.getUsername());
        admin.setAdminIdentifier((long) (Math.random() * 20000 - 0));

        user.setAdmin(admin);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean saveTrainer(User user) throws Exception {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        UserRole userRoleFromDB = userRoleRepository.findByName("ROLE_TRAINER");
        final Trainer trainer = new Trainer();
        final Workout workout = new Workout();

        if (userFromDB != null) {
            return false;
        }
        if(userRoleFromDB != null){
            user.addUserRole(userRoleFromDB);
        }else{
            user.addUserRole(new UserRole("ROLE_TRAINER", false, true));
        }

        trainer.setUser(user);
        trainer.setUsername(user.getUsername());
        trainer.setNameGroup(user.getGroup());
        trainer.setTrainerIdentifier((long) (Math.random() * 20000 - 0));

        user.setTrainer(trainer);

        workout.setTrainer(trainer);
        workout.setNameWorkout(user.getGroup());



        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        workoutRepository.save(workout);
        return true;
    }

    @Override
    public List<User> findUserByKeyword(String keyword) throws Exception {
        return userRepository.findUserByKeyword(keyword);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,  sort);
        return this.userRepository.findAll(pageable);
    }

    @Override
    public User update(User item) throws Exception {

        return userRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) throws Exception {
        userRepository.deleteById(id);

    }

}
