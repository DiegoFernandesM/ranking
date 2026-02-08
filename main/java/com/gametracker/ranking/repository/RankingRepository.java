package com.gametracker.ranking.repository;

import com.gametracker.ranking.model.Game;
import com.gametracker.ranking.model.Ranking;
import com.gametracker.ranking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    List<Ranking> findByGame(Game game);

    List<Ranking> findByUser(User user);

    List<Ranking> findByGameAndUser(Game game, User user);
}