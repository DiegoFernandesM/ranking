package com.gametracker.ranking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "game_rank_config")
public class GameRankConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private double weightKda;

    private double weightHours;

    private double bronzeLimit;
    private double silverLimit;
    private double goldLimit;
    private double platinumLimit;
    private double diamondLimit;


    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public double getWeightKda() {
        return weightKda;
    }

    public void setWeightKda(double weightKda) {
        this.weightKda = weightKda;
    }

    public double getWeightHours() {
        return weightHours;
    }

    public void setWeightHours(double weightHours) {
        this.weightHours = weightHours;
    }

    public double getBronzeLimit() {
        return bronzeLimit;
    }

    public void setBronzeLimit(double bronzeLimit) {
        this.bronzeLimit = bronzeLimit;
    }

    public double getSilverLimit() {
        return silverLimit;
    }

    public void setSilverLimit(double silverLimit) {
        this.silverLimit = silverLimit;
    }

    public double getPesoHoras() {
        return weightHours;
    }

    public double getPesoKda() {
        return weightKda;
    }
}