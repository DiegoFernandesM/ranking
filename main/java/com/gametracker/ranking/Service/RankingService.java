package com.gametracker.ranking.Service;

import com.gametracker.ranking.DTO.RankingDTO;
import com.gametracker.ranking.DTO.RankingPositionDTO;
import com.gametracker.ranking.model.UserGameStats;
import com.gametracker.ranking.repository.UserGameStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final UserGameStatsRepository statsRepo;

    public double calcularScore(double hoursPlayed, double kda, double pesoHoras, double pesoKda) {
        return Math.log(hoursPlayed + 1) * pesoHoras + (kda * pesoKda);
    }

    public String definirRank(double score) {
        if (score >= 800) return "DIAMOND";
        if (score >= 600) return "PLATINUM";
        if (score >= 400) return "GOLD";
        if (score >= 200) return "SILVER";
        return "BRONZE";
    }

    public List<UserGameStats> rankingPorJogo(Long gameId) {
        return statsRepo.findByGame_IdOrderByScoreDesc(gameId);

    }

    public RankingPositionDTO posicaoDoJogador(Long gameId, Long userId) {

        List<UserGameStats> ranking = statsRepo.findByGame_IdOrderByScoreDesc(gameId);

        for (int i = 0; i < ranking.size(); i++) {
            UserGameStats stats = ranking.get(i);

            if (stats.getUser().getId().equals(userId)) {
                return new RankingPositionDTO(
                        i + 1,
                        stats.getScore(),
                        stats.getRank()
                );
            }
        }

        throw new RuntimeException("Usuario nao encontrado no ranking");
    }
    public List<RankingDTO> rankingPublico(Long gameId) {
        return statsRepo.findByGame_IdOrderByScoreDesc(gameId)
                .stream()
                .map(stats -> new RankingDTO(
                        stats.getUser().getUsername(),
                        stats.getScore(),
                        stats.getRank()
                ))
                .toList();
    }
}
