package com.desafios.DesafiosEducativosBackend.Auth;

import com.desafios.DesafiosEducativosBackend.domain.entities.RefreshToken;
import com.desafios.DesafiosEducativosBackend.domain.entities.Rol;
import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import com.desafios.DesafiosEducativosBackend.repositories.UserRepository;
import com.desafios.DesafiosEducativosBackend.services.RefreshTokenService;
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
    private final RefreshTokenService refreshTokenService;
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        Optional<User> userComplete = userRepository.findByUsername(request.getUsername());
        String token = jwtService.getToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        System.out.println("refresh token : " + refreshToken);
        return AuthResponse.builder().token(token).username(user.getUsername()).firstName(userComplete.get().getFirstName()).lastName(userComplete.get().getLastName()).id(userComplete.get().getId()).refreshToken(refreshToken.getToken()).type("Bearer").gender(userComplete.get().getGender()).build();

    }

//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//        String jwt = jwtUtils.generateJwtToken(userDetails);
//
//        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//
//        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
//
//        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
//                userDetails.getUsername(), userDetails.getEmail(), roles));
//    }
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
