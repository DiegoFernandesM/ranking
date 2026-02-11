package com.gametracker.ranking.Service;

import com.gametracker.ranking.DTO.RankingDTO;
import com.gametracker.ranking.DTO.RankingPositionDTO;
import com.gametracker.ranking.model.UserGameStats;
import com.gametracker.ranking.repository.UserGameStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final UserGameStatsRepository statsRepo;

    public List<UserGameStats> rankingPorJogo(Long gameId) {
        return statsRepo.findByGame_IdOrderByScoreDesc(gameId);
    }

    public RankingPositionDTO posicaoDoJogador(Long gameId, Long userId) {
        List<UserGameStats> ranking = statsRepo.findByGame_IdOrderByScoreDesc(gameId);

        if (ranking.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No rankings found for this game"
            );
        }

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

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User not found in this game ranking"
        );
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