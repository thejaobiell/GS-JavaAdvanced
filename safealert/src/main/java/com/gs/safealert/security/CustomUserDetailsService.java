package com.gs.safealert.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.gs.safealert.model.Usuario;
import com.gs.safealert.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));

        return User.builder()
                .username(usuario.getEmail())
                .password("{noop}" + usuario.getSenha())
                .roles(usuario.getTipoUsuario().toUpperCase())
                .build();
    }
}