package com.example.demo;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> credentials) {

        String username = credentials.get("username");
        String password = credentials.get("password");

        // Demo credentials for the experiment only
        if ("user".equals(username) &&
                "bank123".equals(password)) {

            String token = jwtService.generateToken(username);

            return ResponseEntity.ok(
                    Map.of(
                            "message", "Login successful",
                            "token", token
                    )
            );
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid username or password"));
    }
}