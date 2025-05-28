package com.gs.safealert.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para transferência de dados da localidade")
public class LocalidadeDTO {

	@Schema(accessMode = AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 100, message = "Bairro deve ter no máximo 100 caracteres")
    @Schema(description = "Nome do Bairro", maxLength = 100, example = "Mooca")
    private String bairro;

    @Size(max = 50, message = "Zona deve ter no máximo 50 caracteres")
    @Schema(description = "Nome de qual zona de São Paulo é o bairro", example = "Zona Leste", maxLength = 100)
    private String zona;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
}
