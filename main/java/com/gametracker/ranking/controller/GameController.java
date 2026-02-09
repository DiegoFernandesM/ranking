package com.gametracker.ranking.controller;

import com.gametracker.ranking.Service.ExternalGameApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/test")
public class GameController {

    private final ExternalGameApiService externalGameApiService;

    public GameController(ExternalGameApiService externalGameApiService) {
        this.externalGameApiService = externalGameApiService;
    }

    @GetMapping("/rawg")
    public ResponseEntity<?> testarRawg(@RequestParam String nome) {
        return externalGameApiService.buscarPrimeiroJogo(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

