package com.gametracker.ranking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

  @Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String genre;
}

