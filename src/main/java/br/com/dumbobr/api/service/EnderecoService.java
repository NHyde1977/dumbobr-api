package br.com.dumbobr.api.service;

import br.com.dumbobr.api.dto.EnderecoRequestDTO;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;
import br.com.dumbobr.api.model.Endereco;
import br.com.dumbobr.api.model.Usuario;
import br.com.dumbobr.api.repository.EnderecoRepository;
import br.com.dumbobr.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;

    public EnderecoService(
            EnderecoRepository enderecoRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public EnderecoResponseDTO cadastrarEndereco(EnderecoRequestDTO dto) {
        Usuario usuario = usuarioRepository
                .findById(dto.getUsuarioId())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        Endereco endereco = new Endereco(
                dto.getCep(),
                dto.getLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getCidade(),
                dto.getEstado(),
                usuario
        );

        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        return converterParaDTO(enderecoSalvo);
    }

    public List<EnderecoResponseDTO> listarEnderecos() {
        return enderecoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    private EnderecoResponseDTO converterParaDTO(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getUsuario().getId()
        );
    }
}