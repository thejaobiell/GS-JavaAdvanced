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
    private OcorrenciaRepository oR;

    @Autowired
    private PostagemRepository pR;

    public List<Ocorrencia> listarTodos() {
        return oR.findAll();
    }

    public Ocorrencia buscarPorId(Long id) {
        return oR.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ocorrência não encontrada com id " + id));
    }

    public Ocorrencia salvar(Ocorrencia ocorrencia) {
        Long postagemId = ocorrencia.getPostagem().getId();
        Postagem postagem = pR.findById(postagemId)
                .orElseThrow(() -> new EntityNotFoundException("Postagem não encontrada com id " + postagemId));
        ocorrencia.setPostagem(postagem);
        return oR.save(ocorrencia);
    }

    public Ocorrencia atualizarStatus(Long id, String novoStatus) {
        Ocorrencia ocorrencia = buscarPorId(id);
        ocorrencia.setStatus(Ocorrencia.Status.valueOf(novoStatus));
        return oR.save(ocorrencia);
    }

    public void deletar(Long id) {
        if (!oR.existsById(id)) {
            throw new EntityNotFoundException("Ocorrência não encontrada com id " + id);
        }
        oR.deleteById(id);
    }

    public List<Ocorrencia> buscarPorStatus(String status) {
        return oR.findByStatus(status);
    }

    public List<Ocorrencia> buscarPorPostagem(Long postagemId) {
        return oR.findByPostagemId(postagemId);
    }
}
