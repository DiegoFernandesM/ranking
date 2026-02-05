package com.gametracker.ranking.controller;

import com.gametracker.ranking.DTO.LoginRequestDTO;
import com.gametracker.ranking.Security.JwtService;
import com.gametracker.ranking.model.*;
import com.gametracker.ranking.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepo,
                          JwtService jwtService,
                          PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.gerarToken(user.getEmail());
    }
}
