package com.gametracker.ranking.Service;

import com.gametracker.ranking.DTO.CreateStatsDTO;
import com.gametracker.ranking.exception.ResourceNotFoundException;
import com.gametracker.ranking.model.Game;
import com.gametracker.ranking.model.User;
import com.gametracker.ranking.model.UserGameStats;
import com.gametracker.ranking.repository.GameRepository;
import com.gametracker.ranking.repository.UserGameStatsRepository;
import com.gametracker.ranking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGameStatsService {

    private final UserGameStatsRepository statsRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @Transactional
    public UserGameStats createStats(CreateStatsDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Game game = gameRepository.findById(dto.gameId())
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado"));

        Optional<UserGameStats> existing = statsRepository.findByGame_IdAndUser_Id(dto.gameId(), dto.userId());

        UserGameStats stats;
        if (existing.isPresent()) {
            stats = existing.get();
        } else {
            stats = new UserGameStats();
            stats.setUser(user);
            stats.setGame(game);
        }

        stats.setHoursPlayed(dto.hoursPlayed());
        stats.setKda(dto.kda());
        stats.setScore(dto.score());
        stats.setRank(dto.rank());

        return statsRepository.save(stats);
    }

    public List<UserGameStats> getStatsByUser(User user) {
        return statsRepository.findByUser(user);
    }

    public Optional<UserGameStats> getStatsByUserAndGame(Long userId, Long gameId) {
        return statsRepository.findByGame_IdAndUser_Id(gameId, userId);
    }

    public List<UserGameStats> getRankingByGame(Long gameId) {
        return statsRepository.findByGame_IdOrderByScoreDesc(gameId);
    }
}

