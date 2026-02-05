package com.gametracker.ranking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    private String genre;
    private double rating;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) { // ✅ singular
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) { // ✅ único setter
        this.rating = rating;
    }
}
