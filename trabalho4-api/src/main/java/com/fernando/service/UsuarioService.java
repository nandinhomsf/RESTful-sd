package com.fernando.service;

import com.fernando.exception.EntidadeNaoEncontradaException;
import com.fernando.model.Usuario;
import com.fernando.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    public Usuario recuperarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new EntidadeNaoEncontradaException(
                        (id == 0 ? "Usuario não encontrado." :
                        "Usuario número " + id + " não encontrado.")));
    }

    public List<Usuario> recuperarUsuarios() {
        return usuarioRepository.findAll(Sort.by("id"));
    }
}
