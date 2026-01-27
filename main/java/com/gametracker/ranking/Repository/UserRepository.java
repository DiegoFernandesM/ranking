package com.gametracker.ranking.repository;

import com.gametracker.ranking.model.User;
import com.gametracker.ranking.model.UserGameStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {}


