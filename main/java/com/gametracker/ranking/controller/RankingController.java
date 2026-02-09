package com.gametracker.ranking.controller;

import com.gametracker.ranking.DTO.RankingDTO;
import com.gametracker.ranking.DTO.RankingPositionDTO;
import com.gametracker.ranking.Service.RankingService;
import com.gametracker.ranking.model.Ranking;
import com.gametracker.ranking.model.User;
import com.gametracker.ranking.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;
    private final RankingRepository rankingRepo;

    @GetMapping("/game/{gameId}/me")
    public RankingPositionDTO minhaPosicao(
            @PathVariable Long gameId,
            Authentication auth
    ) {
        User user = (User) auth.getPrincipal();
        return rankingService.posicaoDoJogador(gameId, user.getId());
    }

    @GetMapping("/me")
    public List<Ranking> meusRankings(Authentication auth) {
        User user = (User) auth.getPrincipal();
        System.out.println("USER ID = " + user.getId());
        return rankingRepo.findByUser(user);
    }


    @GetMapping("/game/{gameId}/public")
    public List<RankingDTO> rankingPublico(@PathVariable Long gameId) {
        return rankingService.rankingPublico(gameId);
    }
}

