package com.gametracker.ranking.service;

import com.gametracker.ranking.model.GameRankConfig;
import com.gametracker.ranking.model.UserGameStats;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    public double calcularScore(UserGameStats stats, GameRankConfig config) {
        double horas = stats.getHoursPlayed();
        double kda = stats.getKda();

        return (Math.log(horas + 1) * config.getPesoHoras()) +
                (kda * config.getPesoKda());
    }

    public String definirRank(double score) {
        if (score >= 800) return "DIAMOND";
        if (score >= 600) return "PLATINUM";
        if (score >= 400) return "GOLD";
        if (score >= 200) return "SILVER";
        return "BRONZE";
    }
}