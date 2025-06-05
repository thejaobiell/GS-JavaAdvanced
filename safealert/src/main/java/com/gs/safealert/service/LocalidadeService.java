package com.gs.safealert.service;

import com.gs.safealert.dto.LocalidadeDTO;
import com.gs.safealert.model.Localidade;
import com.gs.safealert.repository.LocalidadeRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadeService {

    @Autowired
    private LocalidadeRepository lR;

    public List<Localidade> listarTodasLocalidade() {
        return lR.findAll();
    }

    public Localidade buscarPorIdLocalidade(Long id) {
        return lR.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Localidade não encontrada com id " + id));
    }

    public List<Localidade> buscarPorBairroLocalidade(String bairro) {
        return lR.buscarPorBairro(bairro);
    }

    public Localidade salvarLocalidade(Localidade localidade) {
        return lR.save(localidade);
    }

    public Localidade atualizarLocalidade(Long id, LocalidadeDTO dto) {
        Localidade existente = buscarPorIdLocalidade(id);
        existente.setBairro(dto.getBairro());
        existente.setZona(dto.getZona());
        return lR.save(existente);
    }

    public void deletarLocalidade(Long id) {
        if (!lR.existsById(id)) {
            throw new EntityNotFoundException("Localidade não encontrada com id " + id);
        }
        lR.deleteById(id);
    }
}
