package com.gametracker.ranking.repository;

import com.gametracker.ranking.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {}



