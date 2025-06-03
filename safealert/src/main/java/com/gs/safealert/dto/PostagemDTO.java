package com.gs.safealert.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(description = "DTO para transferência de dados da Postagem")
public class PostagemDTO {

	@Schema(accessMode = AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "Usuário é obrigatório")
    @Schema(description = "ID do usuário dono da postagem", example = "1")
    private Long usuarioId;

    @NotNull(message = "Evento é obrigatório")
    @Schema(description = "ID do evento associado", example = "1")
    private Long eventoId;

    @NotNull(message = "Localidade é obrigatória")
    @Schema(description = "ID da localidade associada", example = "1")
    private Long localidadeId;

    @Size(max = 100, message = "Título deve ter no máximo 100 caracteres")
    @Schema(description = "Título da postagem", example = "Alagamento na região")
    private String titulo;

    @Size(max = 280, message = "Descrição deve ter no máximo 280 caracteres")
    @Schema(description = "Descrição detalhada da postagem", example = "Rua alagada depois da chuva")
    private String descricao;

    @Size(max = 255, message = "URL da imagem deve ter no máximo 255 caracteres")
    @Schema(description = "URL da imagem relacionada à postagem (IREMOS USAR A API DO IMGUR.COM)", example = "https://i.imgur.com/ksaRZbM.jpeg")
    private String imagemUrl;

    @Schema(description = "Data de criação da postagem")
    private LocalDateTime dataCriacao;
    
    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public Long getLocalidadeId() {
        return localidadeId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public void setLocalidadeId(Long localidadeId) {
        this.localidadeId = localidadeId;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}