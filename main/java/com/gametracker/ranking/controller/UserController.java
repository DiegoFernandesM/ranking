package com.gametracker.ranking.controller;

import com.gametracker.ranking.model.User;
import com.gametracker.ranking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repo;

    @PostMapping
    public User save(@RequestBody User user) {
        return repo.save(user);
    }

    @GetMapping
    public List<User> list() {
        return repo.findAll();
    }
}