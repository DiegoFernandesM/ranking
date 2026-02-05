package com.gametracker.ranking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") //
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    @Column(unique = true)
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String password;

    public String getName() {
        return name;
    }
}
