package com.gametracker.ranking.DTO;

public class RankingPositionDTO {

    private int position;
    private double score;
    private String rank;

    public RankingPositionDTO(int position, double score, String rank) {
        this.position = position;
        this.score = score;
        this.rank = rank;
    }

    public int getPosition() { return position; }
    public double getScore() { return score; }
    public String getRank() { return rank; }
}
