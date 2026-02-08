package com.gametracker.ranking.DTO;

public record CreateStatsDTO(
        Long userId,
        Long gameId,
        double hoursPlayed,
        double kda,
        double score,
        String rank
) {}

