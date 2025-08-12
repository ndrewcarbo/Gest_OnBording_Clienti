package com.example.onBoarding_syst.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onBoarding_syst.model.LoginDTO;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        if ("username".equals(login.getUsername()) &&
            "password".equals(login.getPassword())) {

            String token = jwtService.generateToken(login.getUsername());
            return ResponseEntity.ok(new Risposta(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenziali non valide");
    }
}
