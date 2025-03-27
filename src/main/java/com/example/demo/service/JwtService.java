package com.example.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Lazy
    @Autowired
    private UserService userService;

    @Value("${jwt.secret}")
    private String secretKey;

    private final long expirationTime = 1000 * 60 * 60; // 1 ora

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String generateToken(String email) {
        String nome = userService.getUserFullName(email).split(" ")[0];
        String cognome = userService.getUserFullName(email).split(" ")[1];

        return Jwts.builder()
                .setSubject(email)
                .claim("nome", nome)       // Aggiungi il nome
                .claim("cognome", cognome) // Aggiungi il cognome
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 Estrai Nome e Cognome dal token
    public String extractNome(String token) {
        return extractClaim(token, claims -> claims.get("nome", String.class));
    }

    public String extractCognome(String token) {
        return extractClaim(token, claims -> claims.get("cognome", String.class));
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractEmail(token); // Estrai l'email dal token
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
