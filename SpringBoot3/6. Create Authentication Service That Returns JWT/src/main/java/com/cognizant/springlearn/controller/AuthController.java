package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.springlearn.security.JwtGenerator;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtGenerator jwtGenerator;

    @GetMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        
        LOGGER.info("Start of /authenticate endpoint processing");

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            LOGGER.error("Authorization header is missing or not a Basic Auth header");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            String base64Credentials = authHeader.substring(6).trim();
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String credentialsString = new String(decodedBytes, StandardCharsets.UTF_8);

            String[] values = credentialsString.split(":", 2);
            if (values.length != 2) {
                LOGGER.error("Invalid credentials format");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            String username = values[0];
            String password = values[1];

            LOGGER.debug("Extracted credentials - Username: {}, Password: [PROTECTED]", username);

            if ("user".equals(username) && "pwd".equals(password)) {
                LOGGER.info("Authentication successful for user: {}", username);
                
                String token = jwtGenerator.generateToken(username);
                
                Map<String, String> response = new HashMap<>();
                response.put("token", token);

                LOGGER.info("End of /authenticate endpoint processing. Token generated successfully.");
                return ResponseEntity.ok(response);
            } else {
                LOGGER.warn("Authentication failed: invalid username or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to decode base64 Authorization header: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
