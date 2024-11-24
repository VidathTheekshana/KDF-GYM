package com.victorp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameWorkout;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_trainer_id")
    @MapsId
    private Trainer trainer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "workout", cascade = CascadeType.ALL)
    private List<WorkoutPersonal> workoutPersonalList;

    @OneToOne(mappedBy = "workout", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WorkoutGroup workoutGroup;

    public Workout() {
    }

    public Workout(String nameWorkout, Trainer trainer) {
        this.nameWorkout = nameWorkout;
        this.trainer = trainer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameWorkout() {
        return nameWorkout;
    }

    public void setNameWorkout(String nameWorkout) {
        this.nameWorkout = nameWorkout;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<WorkoutPersonal> getWorkoutPersonalList() {
        return workoutPersonalList;
    }

    public void setWorkoutPersonalList(List<WorkoutPersonal> workoutPersonalList) {
        this.workoutPersonalList = workoutPersonalList;
    }

    public void addWorkoutPersonal(WorkoutPersonal workoutPersonal) {
        this.workoutPersonalList.add(workoutPersonal);
    }

    public WorkoutGroup getWorkoutGroup() {
        return workoutGroup;
    }

    public void setWorkoutGroup(WorkoutGroup workoutGroup) {
        this.workoutGroup = workoutGroup;
    }

    @Override
    public String toString() {
        return "Workout{"
                + nameWorkout + '\'' +
                '}';
    }
}
