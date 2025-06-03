package com.gs.safealert.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(description = "DTO para transferência de dados da Ocorrência")
public class OcorrenciaDTO {

	@Schema(accessMode = AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "ID da postagem é obrigatório")
    @Schema(description = "ID da postagem relacionada")
    private Long postagemId;

    @NotNull(message = "Status é obrigatório")
    @Schema(description = "Status da ocorrência", allowableValues = {"Antiga", "Recente", "Atual"})
    private String status;

    @Schema(description = "Data da ocorrência")
    private LocalDateTime dataOcorrencia;

    public Long getId() {
        return id;
    }

    public Long getPostagemId() {
        return postagemId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostagemId(Long postagemId) {
        this.postagemId = postagemId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataOcorrencia(LocalDateTime dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }
}
class OcorrenciaStatusDTO {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

