package com.ats.auth.api;

import com.ats.auth.api.dto.AuthDtos.LoginRequest;
import com.ats.auth.api.dto.AuthDtos.RefreshRequest;
import com.ats.auth.api.dto.AuthDtos.TokenResponse;
import com.ats.auth.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public AuthController(AuthenticationManager authManager, JwtService jwt) {
        this.authManager = authManager;
        this.jwt = jwt;
    }

    // JSON login
    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public TokenResponse loginJson(@RequestBody @Valid LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );
        String name = auth.getName();
        return new TokenResponse(
                jwt.generateAccessToken(name),
                jwt.generateRefreshToken(name),
                "Bearer",
                jwt.getAccessSeconds()
        );
    }

    // Form login
    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public TokenResponse loginForm(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        String name = auth.getName();
        return new TokenResponse(
                jwt.generateAccessToken(name),
                jwt.generateRefreshToken(name),
                "Bearer",
                jwt.getAccessSeconds()
        );
    }

    @PostMapping(value="/refresh", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public TokenResponse refresh(@RequestBody @Valid RefreshRequest req) {
        if (!jwt.isRefresh(req.refreshToken())) {
            throw new IllegalArgumentException("Invalid refresh token");
        }
        String username = jwt.extractUsername(req.refreshToken());
        String newAccess  = jwt.generateAccessToken(username);
        String newRefresh = jwt.generateRefreshToken(username);
        return new TokenResponse(newAccess, newRefresh, "Bearer", jwt.getAccessSeconds());
    }

    // Simple protected endpoint to confirm the token works
    @GetMapping("/me")
    public Map<String, String> me(Authentication auth) {
        return Map.of("user", auth.getName());
    }
}
