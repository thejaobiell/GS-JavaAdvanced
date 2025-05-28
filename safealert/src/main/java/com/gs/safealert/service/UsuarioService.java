package com.gs.safealert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gs.safealert.model.Usuario;
import com.gs.safealert.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository uR;

    public Page<Usuario> listarTodosUsuario(Pageable pageable) {
        return uR.findAll(pageable);
    }

    public Usuario buscarPorIdUsuario(Long id) {
        return uR.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id " + id));
    }
    
    public Usuario buscarPorEmailUsuario(String email) {
    	return uR.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com email " + email));
    }
    
    public List <Usuario> buscarPorEnderecoUsuario(String endereco) {
    	return uR.findByEndereco(endereco);
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return uR.save(usuario);
    }

    public void deletarUsuario(Long id) {
        if (!uR.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com id " + id);
        }
        uR.deleteById(id);
    }
}
