package com.gs.safealert.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostagemDescricaoDTO {
    @Size(max = 280, message = "Descrição deve ter no máximo 280 caracteres")
    @Schema(description = "Descrição detalhada da postagem", example = "Rua alagada depois da chuva")
    @NotBlank(message = "Não pode atualizar e deixar vazio.")
    private String descricaoAtualizada;

	public String getDescricaoAtualizada() {
		return descricaoAtualizada;
	}

	public void setDescricaoAtualizada(String descricaoAtualizada) {
		this.descricaoAtualizada = descricaoAtualizada;
	}
}