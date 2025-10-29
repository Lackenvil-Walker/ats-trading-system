package com.ats.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey key;
    private final long accessSeconds;
    private final long refreshSeconds;

    public JwtService(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.access-minutes}") long accessMinutes,
            @Value("${app.jwt.refresh-days}") long refreshDays
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessSeconds = accessMinutes * 60;
        this.refreshSeconds = refreshDays * 24 * 3600;
    }

    public String generateAccessToken(String username) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(accessSeconds)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String username) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(username)
                .claim("typ", "refresh")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(refreshSeconds)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return parse(token).getBody().getSubject();
    }

    public boolean isRefresh(String token) {
        Object typ = parse(token).getBody().get("typ");
        return "refresh".equals(typ);
    }

    public boolean isValid(String token, UserDetails details) {
        final String username = extractUsername(token);
        return username.equals(details.getUsername()) && !isExpired(token);
    }

    public boolean isExpired(String token) {
        return parse(token).getBody().getExpiration().before(new Date());
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }

    public long getAccessSeconds() { return accessSeconds; }
}
