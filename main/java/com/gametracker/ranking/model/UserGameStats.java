package com.gametracker.ranking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_game_stats")
public class UserGameStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "hours_played", nullable = false)
    private double hoursPlayed;

    @Column(nullable = false)
    private double kda;

    @Column(nullable = false)
    private double score;

    @Column(nullable = false, length = 20)
    private String rank;
}