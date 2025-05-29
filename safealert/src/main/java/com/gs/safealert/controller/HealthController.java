package com.gs.safealert.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Teste de Saude da API")
public class HealthController {

    @GetMapping("/")
    @Operation(summary = "Mensagem de boas-vindas", description = "Retorna uma mensagem indicando que a API está ativa")
    public String hello() {
        return "API do SafeAlert. Adicione para abrir o swagger --> ( /swagger-ui/index.html ) ou teste no postman/insomnia";
    }

    @GetMapping("/health")
    @Operation(summary = "Verificação de saúde da API", description = "Endpoint para verificar se a API está funcionando corretamente")
    public String health() {
        return "OK FUNCIONANDO!";
    }
}
