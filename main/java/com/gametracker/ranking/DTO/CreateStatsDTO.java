package com.gametracker.ranking.DTO;

import jakarta.validation.constraints.*;

public record CreateStatsDTO(
        @NotNull(message = "ID do usuário é obrigatório")
        Long userId,

        @NotNull(message = "ID do jogo é obrigatório")
        Long gameId,

        @PositiveOrZero(message = "Horas jogadas não pode ser negativo")
        double hoursPlayed,

        @PositiveOrZero(message = "KDA não pode ser negativo")
        double kda,

        @PositiveOrZero(message = "Score não pode ser negativo")
        double score,

        @NotBlank(message = "Rank é obrigatório")
        @Size(max = 20, message = "Rank deve ter no máximo 20 caracteres")
        String rank
) {}
