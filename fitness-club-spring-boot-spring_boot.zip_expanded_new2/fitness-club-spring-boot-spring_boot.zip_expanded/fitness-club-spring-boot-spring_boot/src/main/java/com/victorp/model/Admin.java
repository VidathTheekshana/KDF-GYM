package com.victorp.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GenericGenerator(name = "one-one", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))
    @GeneratedValue(generator = "one-one")
    @Column(name = "user_id")
    private Long id;

    @Column
    private String username;

    @Column
    private Long adminIdentifier;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    private User user;

    public Admin() {
    }

    public Admin(String username) {
        this.username = username;
    }


    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;

    }

    public Long getAdminIdentifier() {

        return adminIdentifier;
    }

    public void setAdminIdentifier(Long adminIdentifier) {

        this.adminIdentifier = adminIdentifier;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
