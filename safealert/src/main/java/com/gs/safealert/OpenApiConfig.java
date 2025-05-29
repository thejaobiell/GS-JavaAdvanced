package com.gs.safealert;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "SafeAlert API",
        version = "versão 1",
        description = "📘 **Documentação da API - SafeAlert**\n\n" +
                      "🌪️ **SafeAlert** é uma plataforma voltada para a comunicação e compartilhamento de informações sobre **eventos extremos**, funcionando como uma rede social colaborativa.\n\n" +
                      "🔹 Através da API, usuários podem relatar ocorrências, visualizar alertas em tempo real e interagir com a comunidade.\n\n" +
                      "🧭 **Recomendações de uso:**\n" +
                      "1. Siga a numeração das requisições para entender o fluxo ideal de uso.\n" +
                      "2. Consulte os endpoints disponíveis para autenticação, publicação de alertas, comentários e mais.\n\n"+
                      "**👨‍💻 Membros do Grupo**\n\n"
                      + "João Gabriel Boaventura Marques e Silva – RM554874 – 2TDSB2025\n\n"
                      + "Léo Mota Lima – RM557851 – 2TDSB2025\n\n"
                      + "Lucas Leal das Chagas – RM551124 – 2TDSB2025\n\n"
    )
)
public class OpenApiConfig {}