package com.gametracker.ranking.repository;

import com.gametracker.ranking.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Gamerepository extends JpaRepository<Game, Long> {}


