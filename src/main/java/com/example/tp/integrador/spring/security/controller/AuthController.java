package com.example.tp.integrador.spring.security.controller;

import com.example.tp.integrador.spring.security.dto.AuthLoguinRequestDTO;
import com.example.tp.integrador.spring.security.dto.AuthLoguinResponseDTO;
import com.example.tp.integrador.spring.security.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UserDetailsServiceImp userDetails;

    @PostMapping("/password")
    public ResponseEntity<AuthLoguinResponseDTO> loguin(@RequestBody AuthLoguinRequestDTO authLoguinRequestDTO){
        return ResponseEntity.ok(userDetails.loguin(authLoguinRequestDTO));
    }

    @GetMapping("/oauth")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public String loguinOauth(){
        return "hola";
    }
}
