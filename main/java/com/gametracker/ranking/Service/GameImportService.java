package com.gametracker.ranking.Service;

import com.gametracker.ranking.DTO.RawgGameDTO;
import com.gametracker.ranking.exception.ResourceNotFoundException;
import com.gametracker.ranking.model.Game;
import com.gametracker.ranking.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameImportService {

    private final ExternalGameApiService api;
    private final GameRepository gameRepo;

    @Transactional
    public Game importGame(String gameName) {
        RawgGameDTO dto = api.buscarPrimeiroJogo(gameName)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado na API: " + gameName));

        Optional<Game> existingGame = gameRepo.findFirstByNameIgnoreCase(dto.getName());
        if (existingGame.isPresent()) {
            return existingGame.get();
        }

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
