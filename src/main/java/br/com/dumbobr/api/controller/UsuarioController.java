package br.com.dumbobr.api.controller;

import br.com.dumbobr.api.dto.UsuarioRequestDTO;
import br.com.dumbobr.api.dto.UsuarioResponseDTO;
import br.com.dumbobr.api.model.Endereco;
import br.com.dumbobr.api.model.ObjetoRastreado;
import br.com.dumbobr.api.repository.EnderecoRepository;
import br.com.dumbobr.api.repository.ObjetoRastreadoRepository;
import br.com.dumbobr.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EnderecoRepository enderecoRepository;
    private final ObjetoRastreadoRepository objetoRastreadoRepository;

    public UsuarioController(
            UsuarioService usuarioService,
            EnderecoRepository enderecoRepository,
            ObjetoRastreadoRepository objetoRastreadoRepository
    ) {
        this.usuarioService = usuarioService;
        this.enderecoRepository = enderecoRepository;
        this.objetoRastreadoRepository = objetoRastreadoRepository;
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
    public List<Endereco> listarEnderecosDoUsuario(@PathVariable Long id) {
        return enderecoRepository.findByUsuarioId(id);
    }

    @GetMapping("/{id}/objetos")
    public List<ObjetoRastreado> listarObjetosDoUsuario(@PathVariable Long id) {
        return objetoRastreadoRepository.findByUsuarioId(id);
    }
}