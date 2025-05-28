package com.gs.safealert.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para transferência de dados do usuário")
public class UsuarioDTO {
	
	@Schema(accessMode = AccessMode.READ_ONLY)
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
	@Schema(description = "ID de identificação (Já criado por padrão)", maxLength = 100, example = "Luis Felipe")
	private String nome;

	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Email inválido")
	@Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
	@Schema(description = "Email válido", example = "lf@gmail.com", maxLength = 100)
	private String email;

	@NotBlank(message = "Senha é obrigatória")
	@Size(min = 6, max = 18, message = "Senha deve ter entre 6 e 18 caracteres")
	@Schema(description = "Senha do usuário", example = "senha123", minLength = 6, maxLength = 18)
	private String senha;

	@NotBlank(message = "Endereço é obrigatório")
	@Size(max = 140, message = "Endereço deve ter no máximo 140 caracteres")
	@Pattern(
		regexp = "^[A-Za-zÀ-ÿ0-9\\.\\s]+,\\s*\\d+[A-Za-z]?\\s*-\\s*[A-Za-zÀ-ÿ0-9\\.\\s]+$",
		message = "Endereço deve estar no formato: Rua, Número - Bairro"
	)
	@Schema(description = "Endereço com seguindo este padrão --> Rua, Número - Bairro", example = "Av. Lins de Vasconcelos, 1222 - Aclimação", maxLength = 140)
	private String endereco;

	@Pattern(regexp = "admin|user", message = "Tipo de usuário deve ser 'admin' ou 'user'")
	@Schema(description = "Tipo de usuário (admin ou user). Padrão é user", example = "user", allowableValues = {"admin", "user"})
	private String tipoUsuario = "user";

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
