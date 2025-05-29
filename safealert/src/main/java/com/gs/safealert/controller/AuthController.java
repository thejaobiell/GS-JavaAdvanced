package com.gs.safealert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.gs.safealert.security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação JWT", description = "Geração de token JWT para autenticar nas rotas protegidas da API")
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserDetailsService userDetailsService;

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário", description = "Realiza autenticação com e-mail e senha. Retorna um token JWT caso as credenciais estejam corretas. (OBS: UTILIZE O QUE JÁ ESTÁ NO SCHEMA)", 
            responses = {
                @ApiResponse(responseCode = "200", description = "Token JWT retornado com sucesso",
                    content = @Content(schema = @Schema(implementation = String.class))),
                @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
                @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
            }
        )
    public String login(@RequestBody AuthRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername());
    }

    static class AuthRequest {
        private String username;
        private String password;
        

        @Schema(example = "admin@safealert.com")
        public String getUsername() {
            return username;
        }

        @Schema(example = "2tdsb-2025")
        public String getPassword() {
            return password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
