package com.victorp.controller;

import com.victorp.model.*;
import com.victorp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkoutController {

    private ClientService clientService;

    private UserService userService;

    private TrainerService trainerService;

    private WorkoutService workoutService;

    @Autowired
    public WorkoutController(ClientService clientService, UserService userService, TrainerService trainerService, WorkoutService workoutService) {
        this.clientService = clientService;
        this.userService = userService;
        this.trainerService = trainerService;
        this.workoutService = workoutService;
    }


    @GetMapping("/createWorkoutPersonal")
    public String createWorkoutPersonal( Model model) {

        model.addAttribute("clientForm", new Client());

        return "createWorkoutPersonal";
    }
    @PostMapping("/createWorkoutPersonal")
    public String createWorkoutPersonal(@RequestParam String username, String time, @ModelAttribute("clientForm") @Valid Client clientForm,  BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return "/createWorkoutPersonal";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Workout workout = new Workout();
        Client client = new Client();
        User user = new User();

        WorkoutPersonal workoutPersonal = new WorkoutPersonal();
        workoutPersonal.setWorkout(userService.getByUsername(auth.getName()).getTrainer().getWorkout());
        workoutPersonal.setClient(userService.getByUsername(username).getClient());
        workoutPersonal.setTrainingTime(time);

        workout = userService.getByUsername(auth.getName()).getTrainer().getWorkout();
        client = userService.getByUsername(username).getClient();
        user = userService.getByUsername(username);
        workout.addWorkoutPersonal(workoutPersonal);
        client.setNameGroup(workout.getNameWorkout());
        client.setWorkoutPersonal(workoutPersonal);
        user.setGroup(workout.getNameWorkout());


        workoutService.createWorkoutPersonal(workoutPersonal, workout, client, user );

        return "createWorkoutPersonal";
    }

    @GetMapping("/createWorkoutGroup")
    public String createWorkoutGroup(Model model){

        model.addAttribute("clientForm", new Client());

        return "createWorkoutGroup";
    }
    @PostMapping("/createWorkoutGroup")
    public String createWorkoutGroup(@RequestParam String time) throws Exception{

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Workout workout = new Workout();

        WorkoutGroup workoutGroup = new WorkoutGroup();
        workoutGroup.setWorkout(userService.getByUsername(auth.getName()).getTrainer().getWorkout());
        workoutGroup.setNameWorkout(userService.getByUsername(auth.getName()).getTrainer().getWorkout().getNameWorkout());
        workoutGroup.setClientList(new ArrayList<Client>());
        workoutGroup.setTrainingTime(time);

        workout = userService.getByUsername(auth.getName()).getTrainer().getWorkout();
        workout.setWorkoutGroup(workoutGroup);

        workoutService.createWorkoutGroup(workoutGroup, workout);

        return "createWorkoutGroup";
    }

    @GetMapping("/addClientToGroupWorkout")
    public String addClientToGroupWorkout(Model model, String keyword) throws Exception {

        if(keyword != null){
            model.addAttribute("allClients", clientService.findClientByKeyword(keyword));
        }else{
            model.addAttribute("allClients", clientService.getAllClient());
        }
        return "addClientToGroupWorkout";
    }
    @PostMapping("/addClientToGroupWorkout")
    public String addClientToGroupWorkout(@RequestParam String userName) throws Exception{

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        WorkoutGroup workoutGroup = new WorkoutGroup();
        Client client = new Client();
        Workout workout = new Workout();
        User user = new User();
        Trainer trainer = new Trainer();

        user = userService.getByUsername(userName);
        client = user.getClient();

        trainer = userService.getByUsername(auth.getName()).getTrainer();
        workoutGroup = trainer.getWorkout().getWorkoutGroup();

        workoutGroup.setClientToGroup(client);
        client.setWorkoutGroup(workoutGroup);
        user.setGroup(workoutGroup.getNameWorkout());
        workout.setWorkoutGroup(workoutGroup);

        workoutService.addToWorkoutGroup(workoutGroup, client, workout, user);


        return "addClientToGroupWorkout";
    }

    @GetMapping("/personalWorkoutList")
    public String personalWorkoutList(Model model) throws Exception{

        model.addAttribute("allPersonal", workoutService.getAllPersonal());

        return "personalWorkoutList";
    }
    @PostMapping("/personalWorkoutList")
    public String personalWorkoutList(@RequestParam Long idPersonal) throws Exception{
        workoutService.deleteWorkoutPersonal(idPersonal);

        return "personalWorkoutList";
    }

    @GetMapping("/groupWorkoutList")
    public String groupWorkoutList(Model model) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String nameWorkout;
        nameWorkout = trainerService.getByUsername(auth.getName()).getWorkout().getNameWorkout();

        model.addAttribute("allClientInGroup", clientService.getAllClientInGroup(nameWorkout));

        return "groupWorkoutList";
    }
    @PostMapping("/groupWorkoutList")
    public String groupWorkoutList(@RequestParam String usernameClient) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        WorkoutGroup workoutGroup = new WorkoutGroup();
        Client client = new Client();
        Workout workout = new Workout();
        User user = new User();
        Trainer trainer = new Trainer();

        user = userService.getByUsername(usernameClient);
        client = user.getClient();

        trainer = userService.getByUsername(auth.getName()).getTrainer();
        workoutGroup = trainer.getWorkout().getWorkoutGroup();

        List<Client> clients = new ArrayList<>();
        clients = workoutGroup.getClientList();
        clients.remove(client);
        workoutGroup.setClientList(clients);
        client.setWorkoutGroup(null);
        user.setGroup(null);
        workout.setWorkoutGroup(workoutGroup);

        workoutService.deleteClientFromGroupWorkout(workoutGroup, client, workout, user);

        return "groupWorkoutList";
    }



}
