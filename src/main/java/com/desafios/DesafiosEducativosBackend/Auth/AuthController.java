package com.desafios.DesafiosEducativosBackend.Auth;

import com.desafios.DesafiosEducativosBackend.services.RefreshTokenService;
import com.desafios.DesafiosEducativosBackend.domain.entities.RefreshToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
//@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final RefreshTokenService refreshTokenService;

    private final JwtService jwtUtils;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService, JwtService jwtUtils) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping(value = "refreshtoken")
    public ResponseEntity<?> postRefreshtoken( @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();


        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.getToken(user);
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new IllegalArgumentException(
                        "Refresh token is not in database!"));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
