package com.gs.safealert.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para transferência de dados do Evento")
public class EventoDTO {

	@Schema(accessMode = AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Tipo do evento é obrigatório")
    @Size(max = 50, message = "Tipo deve ter no máximo 50 caracteres")
    @Schema(description = "Tipo do evento", example = "Alagamento")
    private String tipo;

    @Size(max = 120, message = "Descrição deve ter no máximo 120 caracteres")
    @Schema(description = "Descrição do evento", example = "Enchente em área urbana")
    private String descricao;

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
