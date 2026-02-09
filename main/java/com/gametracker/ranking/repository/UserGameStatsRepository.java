package com.gametracker.ranking.repository;

import com.gametracker.ranking.model.UserGameStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserGameStatsRepository extends JpaRepository<UserGameStats, Long> {

    List<UserGameStats> findByGame_IdOrderByScoreDesc(Long gameId);

    Optional<UserGameStats> findByGame_IdAndUser_Id(Long gameId, Long userId);
}