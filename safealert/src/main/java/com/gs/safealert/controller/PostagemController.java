package com.gs.safealert.controller;

import com.gs.safealert.dto.PostagemDTO;
import com.gs.safealert.dto.PostagemDescricaoDTO;
import com.gs.safealert.model.Evento;
import com.gs.safealert.model.Localidade;
import com.gs.safealert.model.Postagem;
import com.gs.safealert.model.Usuario;
import com.gs.safealert.service.PostagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/postagens")
@Tag(name = "4º - Postagem Controller", description = "Operações de CRUD para postagens")
public class PostagemController {

    @Autowired
    private PostagemService pS;

    @GetMapping
    @Operation(summary = "Listar todas as postagens")
    public ResponseEntity<List<PostagemDTO>> listar() {
        List<PostagemDTO> dtos = pS.listarTodos()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar postagem por ID")
    public ResponseEntity<PostagemDTO> buscarPorId(@PathVariable Long id) {
        Postagem postagem = pS.buscarPorId(id);
        return ResponseEntity.ok(toDTO(postagem));
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Buscar postagens por usuário")
    public ResponseEntity<List<PostagemDTO>> buscarPorUsuario(@PathVariable Long usuarioId) {
        List<PostagemDTO> dtos = pS.buscarPorUsuario(usuarioId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/evento/{eventoId}")
    @Operation(summary = "Buscar postagens por evento")
    public ResponseEntity<List<PostagemDTO>> buscarPorEvento(@PathVariable Long eventoId) {
        List<PostagemDTO> dtos = pS.buscarPorEvento(eventoId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/localidade/{localidadeId}")
    @Operation(summary = "Buscar postagens por localidade")
    public ResponseEntity<List<PostagemDTO>> buscarPorLocalidade(@PathVariable Long localidadeId) {
        List<PostagemDTO> dtos = pS.buscarPorLocalidade(localidadeId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    @Operation(summary = "Criar nova postagem")
    public ResponseEntity<PostagemDTO> criar(@Valid @RequestBody PostagemDTO dto) {
        Postagem postagem = toEntity(dto);
        Postagem salvo = pS.salvar(postagem);
        return ResponseEntity.status(201).body(toDTO(salvo));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualizar apenas a descrição da postagem existente")
    public ResponseEntity<PostagemDTO> atualizar(@PathVariable Long id,
                                                 @Valid @RequestBody PostagemDescricaoDTO dto) {
        Postagem atualizado = pS.atualizarDescricao(id, dto.getDescricaoAtualizada());
        return ResponseEntity.ok(toDTO(atualizado));
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletar postagem")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pS.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private PostagemDTO toDTO(Postagem postagem) {
        PostagemDTO dto = new PostagemDTO();
        dto.setId(postagem.getId());
        dto.setUsuarioId(postagem.getUsuario().getId());
        dto.setEventoId(postagem.getEvento().getId());
        dto.setLocalidadeId(postagem.getLocalidade().getId());
        dto.setTitulo(postagem.getTitulo());
        dto.setDescricao(postagem.getDescricao());
        dto.setImagemUrl(postagem.getImagemUrl());
        dto.setDataCriacao(postagem.getDataCriacao());
        return dto;
    }

    private Postagem toEntity(PostagemDTO dto) {
        Postagem postagem = new Postagem();

        if(dto.getId() != null)
            postagem.setId(dto.getId());

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());

        Evento evento = new Evento();
        evento.setId(dto.getEventoId());

        Localidade localidade = new Localidade();
        localidade.setId(dto.getLocalidadeId());

        postagem.setUsuario(usuario);
        postagem.setEvento(evento);
        postagem.setLocalidade(localidade);

        postagem.setTitulo(dto.getTitulo());
        postagem.setDescricao(dto.getDescricao());
        postagem.setImagemUrl(dto.getImagemUrl());

        if(dto.getDataCriacao() != null)
            postagem.setDataCriacao(dto.getDataCriacao());

        return postagem;
    }
}
