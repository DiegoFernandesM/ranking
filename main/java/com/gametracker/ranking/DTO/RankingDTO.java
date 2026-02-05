package com.gametracker.ranking.DTO;
public class RankingDTO {

    private String username;
    private double score;
    private String rank;

    public RankingDTO(String username, double score, String rank) {
        this.username = username;
        this.score = score;
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public double getScore() {
        return score;
    }

    public String getRank() {
        return rank;
    }
}