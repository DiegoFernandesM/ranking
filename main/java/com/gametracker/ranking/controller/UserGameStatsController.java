package com.gametracker.ranking.controller;

import com.gametracker.ranking.DTO.CreateStatsDTO;
import com.gametracker.ranking.model.Game;
import com.gametracker.ranking.model.User;
import com.gametracker.ranking.model.UserGameStats;
import com.gametracker.ranking.repository.GameRepository;
import com.gametracker.ranking.repository.UserGameStatsRepository;
import com.gametracker.ranking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class UserGameStatsController {

    private final UserGameStatsRepository statsRepo;
    private final UserRepository userRepo;
    private final GameRepository gameRepo;

    @PostMapping
    public UserGameStats criarStats(@RequestBody CreateStatsDTO dto) {

        User user = userRepo.findById(dto.userId()).orElseThrow();
        Game game = gameRepo.findById(dto.gameId()).orElseThrow();

        UserGameStats stats = new UserGameStats();
        stats.setUser(user);
        stats.setGame(game);
        stats.setHoursPlayed(dto.hoursPlayed());
        stats.setKda(dto.kda());

        return statsRepo.save(stats);
    }
}