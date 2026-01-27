package com.gametracker.ranking.Controller;

import com.gametracker.ranking.repository.UserRepository;
import com.gametracker.ranking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository repo;

    @PostMapping
    public User save(@RequestBody User u) {
        return repo.save(u);
    }
}