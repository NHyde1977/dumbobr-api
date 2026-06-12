package br.com.dumbobr.api.service;

import br.com.dumbobr.api.model.Usuario;
import br.com.dumbobr.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import br.com.dumbobr.api.dto.UsuarioRequestDTO;
import br.com.dumbobr.api.dto.UsuarioResponseDTO;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
    return usuarioRepository.findAll()
            .stream()
            .map(this::converterParaDTO)
            .toList();
}

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario(
                dto.getNome(),
                dto.getCpf(),
                dto.getEmail(),
                dto.getTelefone()
        );

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return converterParaDTO(usuarioSalvo);
    }

    private UsuarioResponseDTO converterParaDTO(Usuario usuario) {
    return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getCpf(),
            usuario.getEmail(),
            usuario.getTelefone()
    );
    }
}