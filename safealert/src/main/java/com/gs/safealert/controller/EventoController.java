package com.gs.safealert.controller;

import com.gs.safealert.dto.EventoDTO;
import com.gs.safealert.model.Evento;
import com.gs.safealert.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/eventos")
@Tag(name = "3º - Evento Controller", description = "Operações de CRUD para eventos extremos")
public class EventoController {

    @Autowired
    private EventoService eS;

    @GetMapping
    @Operation(summary = "Listar todos os eventos")
    public ResponseEntity<List<EventoDTO>> listar() {
        List<EventoDTO> dtos = eS.listarTodosEvento()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar evento por ID")
    public ResponseEntity<EventoDTO> buscarPorId(@PathVariable Long id) {
        Evento evento = eS.buscarPorIdEvento(id);
        return ResponseEntity.ok(toDTO(evento));
    }

    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Buscar eventos por tipo")
    public ResponseEntity<List<EventoDTO>> buscarPorTipo(@PathVariable String tipo) {
        List<EventoDTO> dtos = eS.buscarPorTipoEvento(tipo)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo evento")
    public ResponseEntity<EventoDTO> criar(@Valid @RequestBody EventoDTO dto) {
        Evento evento = toEntity(dto);
        Evento salvo = eS.salvar(evento);
        return ResponseEntity.status(201).body(toDTO(salvo));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualizar evento existente")
    public ResponseEntity<EventoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody EventoDTO dto) {
        Evento atualizado = eS.atualizarEvento(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(atualizado));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletar evento")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        eS.deletarEvento(id);
        return ResponseEntity.noContent().build();
    }

    private EventoDTO toDTO(Evento evento) {
        EventoDTO dto = new EventoDTO();
        dto.setId(evento.getId());
        dto.setTipo(evento.getTipo());
        dto.setDescricao(evento.getDescricao());
        return dto;
    }

    private Evento toEntity(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setId(dto.getId());
        evento.setTipo(dto.getTipo());
        evento.setDescricao(dto.getDescricao());
        return evento;
    }
}
