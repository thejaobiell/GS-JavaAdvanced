package com.gs.safealert.service;

import com.gs.safealert.model.Evento;
import com.gs.safealert.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eR;

    public Evento salvar(Evento evento) {
        return eR.save(evento);
    }

    public List<Evento> listarTodosEvento() {
        return eR.findAll();
    }

    public Evento buscarPorIdEvento(Long id) {
        return eR.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado com ID " + id));
    }

    public List<Evento> buscarPorTipoEvento(String tipo) {
        return eR.findByTipo(tipo);
    }

    public Evento atualizarEvento(Long id, Evento eventoAtualizado) {
        Evento existente = buscarPorIdEvento(id);
        existente.setTipo(eventoAtualizado.getTipo());
        existente.setDescricao(eventoAtualizado.getDescricao());
        return eR.save(existente);
    }

    public void deletarEvento(Long id) {
        if (!eR.existsById(id)) {
            throw new EntityNotFoundException("Evento não encontrado com ID " + id);
        }
        eR.deleteById(id);
    }
}
