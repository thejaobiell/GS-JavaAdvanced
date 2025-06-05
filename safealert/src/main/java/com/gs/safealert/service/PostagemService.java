package com.gs.safealert.service;

import com.gs.safealert.model.Postagem;
import com.gs.safealert.repository.PostagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository pR;

    public Postagem salvar(Postagem postagem) {
        return pR.save(postagem);
    }

    public Page<Postagem> listarTodos(Pageable pageable) {
        return pR.findAll(pageable);
    }

    public Page<Postagem> buscarPorTituloOuDescricao(String termo, Pageable pageable) {
        return pR.buscarPorTituloOuDescricao(termo, pageable);
    }

    public Postagem buscarPorId(Long id) {
        return pR.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Postagem não encontrada com ID " + id));
    }

    public List<Postagem> buscarPorUsuario(Long usuarioId) {
        return pR.findByUsuarioId(usuarioId);
    }

    public List<Postagem> buscarPorEvento(Long eventoId) {
        return pR.findByEventoId(eventoId);
    }

    public List<Postagem> buscarPorLocalidade(Long localidadeId) {
        return pR.findByLocalidadeId(localidadeId);
    }

    public Postagem atualizarDescricao(Long id, String novaDescricao) {
        Postagem postagem = pR.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Postagem não encontrada"));
        postagem.setDescricao(novaDescricao);
        return pR.save(postagem);
    }

    public void deletar(Long id) {
        if (!pR.existsById(id)) {
            throw new EntityNotFoundException("Postagem não encontrada com ID " + id);
        }
        pR.deleteById(id);
    }
}
