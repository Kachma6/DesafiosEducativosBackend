package com.desafios.DesafiosEducativosBackend.Auth;

import com.desafios.DesafiosEducativosBackend.domain.entities.Rol;
import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import com.desafios.DesafiosEducativosBackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        Optional<User> userComplete = userRepository.findByUsername(request.getUsername());
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).username(user.getUsername()).firstName(userComplete.get().getFirstName()).lastName(userComplete.get().getLastName()).id(userComplete.get().getId()).build();

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
     return AuthResponse.builder().token(jwtService.getToken(user)).username(userDB.getUsername()).firstName(userDB.getFirstName()).lastName(userDB.getLastName()).id(userDB.getId()).gender(userDB.getGender()).build();
    }
}
