package com.gametracker.ranking.model;

import com.gametracker.ranking.model.Game;
import com.gametracker.ranking.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserGameStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Game game;

    private double hoursPlayed;
    private double kda;
    private double score;
    private String rank;
}