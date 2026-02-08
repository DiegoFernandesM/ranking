package com.gametracker.ranking.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ranking", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "game_id"})
})
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Long getId() { return id; }

    public double getScore()
    { return score; }

    public void setScore(double score)
    { this.score = score; }

    public User getUser()
    { return user; }

    public void setUser(User user)
    { this.user = user; }

    public Game getGame()
    { return game; }

    public void setGame(Game game)
    { this.game = game; }

}