package com.gametracker.ranking.controller;

import com.gametracker.ranking.DTO.CreateStatsDTO;
import com.gametracker.ranking.Service.UserGameStatsService;
import com.gametracker.ranking.exception.ResourceNotFoundException;
import com.gametracker.ranking.model.User;
import com.gametracker.ranking.model.UserGameStats;
import com.gametracker.ranking.repository.UserGameStatsRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stats")
@RequiredArgsConstructor
public class UserGameStatsController {

    private final UserGameStatsService statsService;
    private final UserGameStatsRepository statsRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserGameStats criarStats(@Valid @RequestBody CreateStatsDTO dto) {
        return statsService.createStats(dto);
    }

    @PutMapping("/{id}")
    public UserGameStats atualizarStats(@PathVariable Long id, @Valid @RequestBody CreateStatsDTO dto) {
        if (!statsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Estatísticas não encontradas");
        }
        return statsService.createStats(dto);
    }

    @GetMapping("/{id}")
    public UserGameStats getStatsById(@PathVariable Long id) {
        return statsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estatísticas não encontradas"));
    }

    @GetMapping("/user/{userId}")
    public List<UserGameStats> getStatsByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return statsService.getStatsByUser(user);
    }

    @GetMapping("/game/{gameId}")
    public List<UserGameStats> getStatsByGame(@PathVariable Long gameId) {
        return statsService.getRankingByGame(gameId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStats(@PathVariable Long id) {
        if (!statsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Estatísticas não encontradas");
        }
        statsRepository.deleteById(id);
    }
}
