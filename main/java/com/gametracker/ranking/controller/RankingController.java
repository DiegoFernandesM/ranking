package com.gametracker.ranking.controller;

import com.gametracker.ranking.DTO.RankingDTO;
import com.gametracker.ranking.DTO.RankingPositionDTO;
import com.gametracker.ranking.Service.RankingService;
import com.gametracker.ranking.model.Ranking;
import com.gametracker.ranking.model.User;
import com.gametracker.ranking.repository.RankingRepository;
import com.gametracker.ranking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;
    private final UserRepository userRepository;
    private final RankingRepository rankingRepository;

    // =========================
    // TESTE DE AUTENTICAÇÃO
    // =========================
    @GetMapping("/test")
    public String teste(Authentication auth) {
        return "Autenticado como: " + auth.getName();
    }

    // =========================
    // MINHA POSIÇÃO NO JOGO
    // =========================
    @GetMapping("/game/{gameId}/me")
    public RankingPositionDTO minhaPosicao(
            @PathVariable Long gameId,
            Authentication auth
    ) {
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return rankingService.posicaoDoJogador(gameId, user.getId());
    }

    // =========================
    // MEUS RANKINGS
    // =========================
    @GetMapping("/me")
    public List<Ranking> meusRankings(Authentication auth) {
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return rankingRepository.findByUser(user);
    }

    // =========================
    // RANKING PÚBLICO
    // =========================
    @GetMapping("/game/{gameId}/public")
    public List<RankingDTO> rankingPublico(@PathVariable Long gameId) {
        return rankingService.rankingPublico(gameId);
    }
}
