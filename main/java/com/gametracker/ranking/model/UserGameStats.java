package com.gametracker.ranking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class UserGameStats {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Game game;

    public double getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(double hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    private double hoursPlayed;
    private double kda;
    private double score;
    private String rank;
}