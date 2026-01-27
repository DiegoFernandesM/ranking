package com.gametracker.ranking.Controller;

import com.gametracker.ranking.repository.UserGameStatsRepository;
import com.gametracker.ranking.repository.GameRankConfigRepository;
import com.gametracker.ranking.service.RankingService;
import com.gametracker.ranking.model.GameRankConfig;
import com.gametracker.ranking.model.UserGameStats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rank")
public class RankingController {

    @Autowired
    private UserGameStatsRepository statsRepo;

    @Autowired
    private GameRankConfigRepository configRepo;

    @Autowired
    private RankingService service;

    @PostMapping("/stats")
    public UserGameStats saveStats(@RequestBody UserGameStats stats) {
        return statsRepo.save(stats);
    }

    @PostMapping("/update/{id}")
    public UserGameStats updateRank(@PathVariable Long id) {
        UserGameStats stats = statsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Stats não encontrado"));

        GameRankConfig config = configRepo.findByGameId(stats.getGame().getId());

        double score = service.calcularScore(stats, config);
        String rank = service.definirRank(score);

        stats.setScore(score);
        stats.setRank(rank);

        return statsRepo.save(stats);
    }

    @GetMapping("/game/{gameId}")
    public List<UserGameStats> ranking(@PathVariable Long gameId) {
        return statsRepo.findByGameId(gameId);
    }
}