package com.gametracker.ranking.Service;

import com.gametracker.ranking.DTO.RawgGameDTO;
import com.gametracker.ranking.Service.ExternalGameApiService;
import com.gametracker.ranking.model.Game;
import com.gametracker.ranking.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameImportService {

    private final ExternalGameApiService api;
    private final GameRepository gameRepo;

    public Game importar(String nome) {
        RawgGameDTO dto = api.buscarPrimeiroJogo(nome)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado: " + nome));

        Game game = new Game();
        game.setName(dto.getName());
        game.setRating(dto.getRating());

        if (dto.getGenres() != null && !dto.getGenres().isEmpty()) {
            game.setGenre(dto.getGenres().get(0).getName());
        } else {
            game.setGenre("Desconhecido");
        }

        return gameRepo.save(game);
    }
}
