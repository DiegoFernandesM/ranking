package com.gametracker.ranking.controller;

import com.gametracker.ranking.DTO.RankingPositionDTO;
import com.gametracker.ranking.Service.RankingService;
import com.gametracker.ranking.model.GameRankConfig;
import com.gametracker.ranking.model.UserGameStats;
import com.gametracker.ranking.repository.GameRankConfigRepository;
import com.gametracker.ranking.repository.UserGameStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
public class RankingController {

    private final UserGameStatsRepository statsRepo;
    private final GameRankConfigRepository configRepo;
    private final RankingService service;

    @PostMapping("/update/{id}")
    public UserGameStats update(@PathVariable Long id) {

        UserGameStats stats = statsRepo.findById(id).orElseThrow();

        GameRankConfig config =
                configRepo.findByGameId(stats.getGame().getId());

        double score = service.calcularScore(
                stats.getHoursPlayed(),
                stats.getKda(),
                config.getPesoHoras(),
                config.getPesoKda()
        );

        stats.setScore(score);
        stats.setRank(service.definirRank(score));

        return statsRepo.save(stats);
    }

    @GetMapping("/game/{gameId}")
    public List<UserGameStats> rankingPorJogo(@PathVariable Long gameId) {
        return service.rankingPorJogo(gameId);
    }
    @GetMapping("/game/{gameId}/user/{userId}")
    public RankingPositionDTO posicaoDoJogador(
            @PathVariable Long gameId,
            @PathVariable Long userId
    ) {
        return service.posicaoDoJogador(gameId, userId);
    }

}
