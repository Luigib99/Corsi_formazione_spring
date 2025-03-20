package com.example.demo;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;

public class GenerateSecretKey {
    public static void main(String[] args) {
        // Usa il metodo 'secretKeyFor' per ottenere una chiave segreta adeguata
        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Stampa la chiave segreta in Base64
        String encodedSecret = java.util.Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("La chiave segreta codificata in Base64 è: " + encodedSecret);
    }
}

