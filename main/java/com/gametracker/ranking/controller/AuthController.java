package com.gametracker.ranking.controller;

import com.gametracker.ranking.DTO.LoginRequestDTO;
import com.gametracker.ranking.DTO.RegisterRequestDTO;
import com.gametracker.ranking.Security.JwtService;
import com.gametracker.ranking.exception.DuplicateResourceException;
import com.gametracker.ranking.exception.ResourceNotFoundException;
import com.gametracker.ranking.exception.UnauthorizedException;
import com.gametracker.ranking.model.*;
import com.gametracker.ranking.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
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
    public LoginResponse login(@Valid @RequestBody LoginRequestDTO request) {

        User user = userRepo.findByEmail(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UnauthorizedException("Senha inválida");
        }

        String token = jwtService.gerarToken(user.getEmail());
        return new LoginResponse(token, user.getId(), user.getUsername(), user.getEmail());
    }

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequestDTO request) {

        if (userRepo.findByEmail(request.email()).isPresent()) {
            throw new DuplicateResourceException("Email já cadastrado");
        }

        User user = new User();
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepo.save(user);

        return new RegisterResponse("Usuário criado com sucesso", user.getId(), user.getUsername());
    }

    public record LoginResponse(String token, Long userId, String username, String email) {}
    public record RegisterResponse(String message, Long userId, String username) {}
}
