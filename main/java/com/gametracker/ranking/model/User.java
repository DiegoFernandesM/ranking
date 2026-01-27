package com.gametracker.ranking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // opcional
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;}