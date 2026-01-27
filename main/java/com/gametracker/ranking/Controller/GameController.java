package com.gametracker.ranking.controller;

import com.gametracker.ranking.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    com.gametracker.ranking.repository.Gamerepository repo;

    @PostMapping
    public Game save(@RequestBody Game g) {
        return repo.save(g);
    }

    @GetMapping
    public List<Game> list() {
        return repo.findAll();
    }
}