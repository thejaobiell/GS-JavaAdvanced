package com.gs.safealert;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "SafeAlert API",
        version = "v1",
        description = "Documentação da API da plataforma SafeAlert. "
        		+ "Siga a ordem dos números"
    )
)
public class OpenApiConfig {
}
