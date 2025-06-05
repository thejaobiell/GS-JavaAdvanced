package com.gs.safealert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gs.safealert.dto.UsuarioDTO;
import com.gs.safealert.model.Usuario;
import com.gs.safealert.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "1º - Usuario Controller", description = "Operações de CRUD para usuário")
public class UsuarioController {

    @Autowired
    private UsuarioService uS;

    @GetMapping
    @Operation(summary = "Lista todos os usuários com paginação")
    @Parameters({
            @Parameter(name = "page", description = "Número da página (0-base)"),
            @Parameter(name = "size", description = "Quantidade de elementos por página"),
            @Parameter(name = "sort", description = "Campo para ordenação, ex: nome,asc ou nome,desc")
    })
    public List<UsuarioDTO> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return uS.listarTodosUsuario(pageable)
                .map(this::converterParaDTO)
                .getContent();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar por ID")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        Usuario user = uS.buscarPorIdUsuario(id);
        return ResponseEntity.ok(converterParaDTO(user));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar por EMAIL")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@PathVariable String email) {
        Usuario user = uS.buscarPorEmailUsuario(email);
        return ResponseEntity.ok(converterParaDTO(user));
    }

    @GetMapping("/endereco/{endereco}")
    @Operation(summary = "Buscar por ENDEREÇO (PODENDO PESQUISAR BAIRRO OU RUA)")
    public ResponseEntity<List<UsuarioDTO>> buscarPorEndereco(@PathVariable String endereco) {
        List<Usuario> users = uS.buscarPorEnderecoUsuario(endereco);
        List<UsuarioDTO> dtos = users.stream()
                .map(this::converterParaDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    @Operation(summary = "Cria usuário")
    public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioDTO dto) {
        Usuario user = converterParaEntidade(dto);
        Usuario salvo = uS.salvarUsuario(user);
        return ResponseEntity.status(201).body(converterParaDTO(salvo));
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualiza um usuário existente")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        Usuario usuario = converterParaEntidade(dto);
        Usuario atualizado = uS.atualizarUsuario(id, usuario);
        return ResponseEntity.ok(converterParaDTO(atualizado));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleta usuário")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        uS.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    private UsuarioDTO converterParaDTO(Usuario user) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(user.getId());
        dto.setNome(user.getNome());
        dto.setEmail(user.getEmail());
        dto.setEndereco(user.getEndereco());
        dto.setSenha(user.getSenha());
        dto.setTipoUsuario(user.getTipoUsuario());
        return dto;
    }

    private Usuario converterParaEntidade(UsuarioDTO dto) {
        Usuario user = new Usuario();
        user.setId(dto.getId());
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha(dto.getSenha());
        user.setEndereco(dto.getEndereco());
        user.setTipoUsuario(dto.getTipoUsuario());
        return user;
    }
}
