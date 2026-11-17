package com.example.demo;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final JwtService jwtService;

    public AccountController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/details")
    public ResponseEntity<?> getAccountDetails(
            @RequestHeader(
                    value = "Authorization",
                    required = false
            ) String authorization) {

        if (authorization == null ||
                !authorization.startsWith("Bearer ")) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Missing or invalid token"));
        }

        String token = authorization.substring(7);

        try {
            if (!jwtService.isTokenValid(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Invalid or expired token"));
            }

            String username = jwtService.extractUsername(token);

            return ResponseEntity.ok(
                    Map.of(
                            "accountHolder", username,
                            "accountNumber", "XXXX-XXXX-1234",
                            "accountType", "Savings",
                            "balance", 25000.00
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or expired token"));
        }
    }
}