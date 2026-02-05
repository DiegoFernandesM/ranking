package com.gametracker.ranking.controller;

import com.gametracker.ranking.Service.GameImportService;
import com.gametracker.ranking.model.Game;
import com.gametracker.ranking.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {


    private final GameRepository repo;


    private final GameImportService GameImportService;

    @PostMapping
    public Game save(@RequestBody Game game) {
        return repo.save(game);
    }

    @GetMapping
    public List<Game> list() {
        return repo.findAll();
    }

    @PostMapping("/import/{nome}")
    public Game importar(@PathVariable String nome) {
        return GameImportService.importar(nome);
    }
}
