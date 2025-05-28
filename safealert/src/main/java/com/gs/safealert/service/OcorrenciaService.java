package com.gs.safealert.service;

import com.gs.safealert.model.Ocorrencia;
import com.gs.safealert.model.Postagem;
import com.gs.safealert.repository.OcorrenciaRepository;
import com.gs.safealert.repository.PostagemRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    public List<Ocorrencia> listarTodos() {
        return ocorrenciaRepository.findAll();
    }

    public Ocorrencia buscarPorId(Long id) {
        return ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ocorrência não encontrada com id " + id));
    }

    public Ocorrencia salvar(Ocorrencia ocorrencia) {
        // Validação da postagem vinculada
        Long postagemId = ocorrencia.getPostagem().getId();
        Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new EntityNotFoundException("Postagem não encontrada com id " + postagemId));
        ocorrencia.setPostagem(postagem);
        return ocorrenciaRepository.save(ocorrencia);
    }

    public Ocorrencia atualizar(Long id, Ocorrencia ocorrenciaAtualizada) {
        Ocorrencia ocorrencia = buscarPorId(id);
        ocorrencia.setStatus(ocorrenciaAtualizada.getStatus());
        if (ocorrenciaAtualizada.getDataOcorrencia() != null) {
            ocorrencia.setDataOcorrencia(ocorrenciaAtualizada.getDataOcorrencia());
        }
        return ocorrenciaRepository.save(ocorrencia);
    }

    public void deletar(Long id) {
        if (!ocorrenciaRepository.existsById(id)) {
            throw new EntityNotFoundException("Ocorrência não encontrada com id " + id);
        }
        ocorrenciaRepository.deleteById(id);
    }

    public List<Ocorrencia> buscarPorStatus(String status) {
        return ocorrenciaRepository.findByStatus(status);
    }

    public List<Ocorrencia> buscarPorPostagem(Long postagemId) {
        return ocorrenciaRepository.findByPostagemId(postagemId);
    }
}
