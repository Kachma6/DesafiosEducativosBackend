package com.desafios.DesafiosEducativosBackend.services.implement;

import com.desafios.DesafiosEducativosBackend.domain.DTOS.AuthResponse;
import com.desafios.DesafiosEducativosBackend.Auth.JwtService;
import com.desafios.DesafiosEducativosBackend.domain.DTOS.LoginRequest;
import com.desafios.DesafiosEducativosBackend.domain.DTOS.RegisterRequest;
import com.desafios.DesafiosEducativosBackend.domain.entities.RefreshToken;
import com.desafios.DesafiosEducativosBackend.domain.entities.Rol;
import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import com.desafios.DesafiosEducativosBackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        Optional<User> userComplete = userRepository.findByUsername(request.getUsername());
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .username(user.getUsername())
                .firstName(userComplete.get().getFirstName())
                .lastName(userComplete.get().getLastName())
                .id(userComplete.get().getId())
                .refreshToken(refreshTokenService.createRefreshToken(user.getId()).getToken())
                .type("Bearer")
                .gender(userComplete.get().getGender()).build();

    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .rol(Rol.USER)
                .gender(request.getGender())
                .createAt(LocalDateTime.now())
                .build();
        User userDB =  userRepository.save(user);
     return AuthResponse.builder()
             .token(jwtService.getToken(user))
             .refreshToken(refreshTokenService.createRefreshToken(user.getId()).getToken())
             .username(userDB.getUsername())
             .firstName(userDB.getFirstName())
             .lastName(userDB.getLastName())
             .id(userDB.getId())
             .gender(userDB.getGender())
             .build();
    }
}
