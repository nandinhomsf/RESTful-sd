package com.fernando.controller;

import com.fernando.model.Usuario;
import com.fernando.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@AllArgsConstructor
@RequestMapping("usuarios")    // http://localhost:8080/categorias
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> recuperarUsuarios() {
        return usuarioService.recuperarUsuarios();
    }

    @GetMapping("{idUsuario}")         // http://localhost:8080/categorias/1
    public Usuario recuperarUsuarioPorId(@PathVariable("idUsuario") Long id) {
        return usuarioService.recuperarUsuarioPorId(id);
    }
}
