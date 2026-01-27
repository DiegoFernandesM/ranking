package com.gametracker.ranking.repository;



import com.gametracker.ranking.model.UserGameStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGameStatsRepository
        extends JpaRepository<UserGameStats, Long> {
    List<UserGameStats> findByGameId(Long gameId);
}
