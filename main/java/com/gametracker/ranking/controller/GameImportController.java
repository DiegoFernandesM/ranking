package com.gametracker.ranking.controller;

import com.gametracker.ranking.Service.GameImportService;
import com.gametracker.ranking.exception.ResourceNotFoundException;
import com.gametracker.ranking.model.Game;
import com.gametracker.ranking.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameImportController {

    private final GameImportService gameImportService;
    private final GameRepository gameRepository;

    @PostMapping("/import")
    @ResponseStatus(HttpStatus.CREATED)
    public Game importGame(@RequestParam String gameName) {
        return gameImportService.importGame(gameName);
    }

    @GetMapping
    public List<Game> listGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado"));
    }

    @GetMapping("/info")
    public String info() {
        return "Game Import API - Use POST /api/v1/games/import?gameName=nome_do_jogo";
    }
}

