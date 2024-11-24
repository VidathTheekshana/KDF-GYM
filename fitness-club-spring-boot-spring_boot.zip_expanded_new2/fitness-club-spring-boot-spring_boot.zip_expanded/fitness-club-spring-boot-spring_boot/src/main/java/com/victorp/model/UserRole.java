package com.victorp.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity

@Table(name = "user_role")
public class UserRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean admin;

    @Column
    private Boolean trainer;

    @ManyToMany(mappedBy = "userRole", fetch = FetchType.EAGER)
    private Set<User> users;

    public UserRole() {
    }

    public UserRole(String name, Boolean admin, Boolean trainer) {
        this.name = name;
        this.admin = admin;
        this.trainer = trainer;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAdmin() {

        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setTrainer(Boolean trainer) {

        this.trainer = trainer;
    }

    public Boolean getTrainer() {

        return trainer;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {

        this.users.add(user);
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
