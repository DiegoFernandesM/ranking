package com.gametracker.ranking.repository;

import com.gametracker.ranking.model.GameRankConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface GameRankConfigRepository extends JpaRepository<GameRankConfig, Long> {

    GameRankConfig findByGameId(Long gameId);

}
