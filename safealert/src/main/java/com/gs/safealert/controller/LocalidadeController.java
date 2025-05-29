package com.gs.safealert.controller;

import com.gs.safealert.dto.LocalidadeDTO;
import com.gs.safealert.model.Localidade;
import com.gs.safealert.service.LocalidadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/localidades")
@Tag(name = "2º - Localidade Controller", description = "Operações de CRUD para localidades")
public class LocalidadeController {

    @Autowired
    private LocalidadeService lS;

    @GetMapping
    @Operation(summary = "Listar todas as localidades")
    public ResponseEntity<List<LocalidadeDTO>> listarTodas() {
        List<LocalidadeDTO> lista = lS.listarTodasLocalidade()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar localidade por ID")
    public ResponseEntity<LocalidadeDTO> buscarPorId(@PathVariable Long id) {
        Localidade localidade = lS.buscarPorIdLocalidade(id);
        return ResponseEntity.ok(converterParaDTO(localidade));
    }

    @GetMapping("/bairro/{bairro}")
    @Operation(summary = "Buscar localidades por bairro (contendo)")
    public ResponseEntity<List<LocalidadeDTO>> buscarPorBairro(@PathVariable String bairro) {
        List<LocalidadeDTO> lista = lS.buscarPorBairroLocalidade(bairro)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Operation(summary = "Criar nova localidade")
    public ResponseEntity<LocalidadeDTO> criar(@Valid @RequestBody LocalidadeDTO dto) {
        Localidade salvo = lS.salvarLocalidade(converterParaEntidade(dto));
        return ResponseEntity.status(201).body(converterParaDTO(salvo));
    }
    
    @PutMapping("/update/{id}")
    @Operation(summary = "Atualizar localidade por ID")
    public ResponseEntity<LocalidadeDTO> atualizar(@PathVariable Long id, @Valid @RequestBody LocalidadeDTO dto) {
        Localidade existente = lS.buscarPorIdLocalidade(id);
        existente.setBairro(dto.getBairro());
        existente.setZona(dto.getZona());

        Localidade atualizado = lS.salvarLocalidade(existente);
        return ResponseEntity.ok(converterParaDTO(atualizado));
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletar localidade por ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        lS.deletarLocalidade(id);
        return ResponseEntity.noContent().build();
    }

    private LocalidadeDTO converterParaDTO(Localidade l) {
    	LocalidadeDTO dto = new LocalidadeDTO();
    	dto.setId(l.getId());
    	dto.setBairro(l.getBairro());
    	dto.setZona(l.getZona());
    	return dto;
    }

    private Localidade converterParaEntidade(LocalidadeDTO dto) {
        Localidade local = new Localidade();
        local.setId(dto.getId());
        local.setBairro(dto.getBairro());
        local.setZona(dto.getZona());
    	return local;
    }
}
