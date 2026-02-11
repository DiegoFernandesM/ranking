package com.gametracker.ranking.controller;

import com.gametracker.ranking.DTO.RankingDTO;
import com.gametracker.ranking.DTO.RankingPositionDTO;
import com.gametracker.ranking.Service.RankingService;
import com.gametracker.ranking.model.User;
import com.gametracker.ranking.model.UserGameStats;
import com.gametracker.ranking.repository.UserGameStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;
    private final UserGameStatsRepository statsRepo;

    @GetMapping("/game/{gameId}/me")
    public RankingPositionDTO minhaPosicao(
            @PathVariable Long gameId,
            Authentication auth
    ) {
        User user = obterUsuarioAutenticado(auth);
        return rankingService.posicaoDoJogador(gameId, user.getId());
    }

    @GetMapping("/user/me")
    public List<UserGameStats> meusRankings(Authentication auth) {
        User user = obterUsuarioAutenticado(auth);
        return statsRepo.findByUser(user);
    }

    @GetMapping("/public")
    public List<RankingDTO> rankingPublico(@RequestParam Long gameId) {
        return rankingService.rankingPublico(gameId);
    }

    @GetMapping("/game/{gameId}")
    public List<RankingDTO> rankingDoJogo(@PathVariable Long gameId) {
        return rankingService.rankingPublico(gameId);
    }

    private User obterUsuarioAutenticado(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "User not authenticated"
            );
        }

        Object principal = auth.getPrincipal();
        if (!(principal instanceof User)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid user principal"
            );
        }

        return (User) principal;
    }
}
