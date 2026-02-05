package com.gametracker.ranking.controller;

import com.gametracker.ranking.model.Game;
import com.gametracker.ranking.model.User;
import com.gametracker.ranking.model.UserGameStats;
import com.gametracker.ranking.repository.GameRepository;
import com.gametracker.ranking.repository.UserGameStatsRepository;
import com.gametracker.ranking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class UserGameStatsController {

    private final UserGameStatsRepository statsRepo;
    private final UserRepository userRepo;
    private final GameRepository gameRepo;

    @PostMapping
    public UserGameStats create(@RequestBody UserGameStats stats) {

        User user = userRepo.findById(stats.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User não encontrado"));

        Game game = gameRepo.findById(stats.getGame().getId())
                .orElseThrow(() -> new RuntimeException("Game não encontrado"));

        stats.setUser(user);
        stats.setGame(game);

        return statsRepo.save(stats);
    }

    @GetMapping
    public List<UserGameStats> list() {
        return statsRepo.findAll();
    }
}