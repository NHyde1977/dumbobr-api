package br.com.dumbobr.api.controller;

import br.com.dumbobr.api.dto.UsuarioRequestDTO;
import br.com.dumbobr.api.dto.UsuarioResponseDTO;
import br.com.dumbobr.api.model.ObjetoRastreado;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;
import br.com.dumbobr.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    public UsuarioResponseDTO cadastrarUsuario(
            @RequestBody @Valid UsuarioRequestDTO dto
    ) {
        return usuarioService.cadastrarUsuario(dto);
    }

    @GetMapping("/{id}/enderecos")
    public List<EnderecoResponseDTO> listarEnderecosDoUsuario(@PathVariable Long id) {
        return usuarioService.listarEnderecosDoUsuario(id);
    }

    @GetMapping("/{id}/objetos")
    public List<ObjetoRastreadoResponseDTO> listarObjetosDoUsuario(@PathVariable Long id) {
        return usuarioService.listarObjetosDoUsuario(id);
    }
}