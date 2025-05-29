package com.gs.safealert.controller;

import com.gs.safealert.dto.OcorrenciaDTO;
import com.gs.safealert.model.Ocorrencia;
import com.gs.safealert.model.Postagem;
import com.gs.safealert.service.OcorrenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ocorrencias")
@Tag(name = "5º - Ocorrencia Controller", description = "Operações de CRUD para ocorrências")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService oS;

    @GetMapping
    @Operation(summary = "Listar todas as ocorrências")
    public ResponseEntity<List<OcorrenciaDTO>> listar() {
        List<OcorrenciaDTO> dtos = oS.listarTodos()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar ocorrência por ID")
    public ResponseEntity<OcorrenciaDTO> buscarPorId(@PathVariable Long id) {
        Ocorrencia ocorrencia = oS.buscarPorId(id);
        return ResponseEntity.ok(toDTO(ocorrencia));
    }

    @PostMapping
    @Operation(summary = "Criar nova ocorrência")
    public ResponseEntity<OcorrenciaDTO> criar(@Valid @RequestBody OcorrenciaDTO dto) {
        Ocorrencia ocorrencia = toEntity(dto);
        Ocorrencia salvo = oS.salvar(ocorrencia);
        return ResponseEntity.status(201).body(toDTO(salvo));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualizar ocorrência")
    public ResponseEntity<OcorrenciaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody OcorrenciaDTO dto) {
        Ocorrencia atualizado = oS.atualizar(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(atualizado));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletar ocorrência")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        oS.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
        OcorrenciaDTO dto = new OcorrenciaDTO();
        dto.setId(ocorrencia.getId());
        dto.setPostagemId(ocorrencia.getPostagem().getId());
        dto.setStatus(ocorrencia.getStatus().name());
        dto.setDataOcorrencia(ocorrencia.getDataOcorrencia());
        return dto;
    }

    private Ocorrencia toEntity(OcorrenciaDTO dto) {
        Ocorrencia ocorrencia = new Ocorrencia();
        if (dto.getId() != null) {
            ocorrencia.setId(dto.getId());
        }

        Postagem postagem = new Postagem();
        postagem.setId(dto.getPostagemId());
        ocorrencia.setPostagem(postagem);

        if (dto.getStatus() != null) {
            ocorrencia.setStatus(Ocorrencia.Status.valueOf(dto.getStatus()));
        }

        if (dto.getDataOcorrencia() != null) {
            ocorrencia.setDataOcorrencia(dto.getDataOcorrencia());
        }

        return ocorrencia;
    }
}
