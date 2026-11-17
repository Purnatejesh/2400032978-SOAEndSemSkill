package com.example.demo;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ProfileController {

    @GetMapping("/profile")
    public Map<String, String> getProfile() {
        return Map.of(
            "name", "Tejesh",
            "role", "Student",
            "message", "Profile API is working"
        );
    }
}