package br.com.dumbobr.api.service;

import br.com.dumbobr.api.dto.UsuarioResponseDTO;
import br.com.dumbobr.api.model.Usuario;
import br.com.dumbobr.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;
import br.com.dumbobr.api.model.ObjetoRastreado;
import br.com.dumbobr.api.repository.ObjetoRastreadoRepository;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;
import br.com.dumbobr.api.model.Endereco;
import br.com.dumbobr.api.repository.EnderecoRepository;

import java.util.List;

@Service
public class MeService {

    private final UsuarioRepository usuarioRepository;
    private final ObjetoRastreadoRepository objetoRastreadoRepository;
    private final EnderecoRepository enderecoRepository;

    public MeService(
        UsuarioRepository usuarioRepository,
        ObjetoRastreadoRepository objetoRastreadoRepository,
        EnderecoRepository enderecoRepository
    ) {
    this.usuarioRepository = usuarioRepository;
    this.objetoRastreadoRepository = objetoRastreadoRepository;
    this.enderecoRepository = enderecoRepository;
    }

    public UsuarioResponseDTO obterUsuarioLogado(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getTelefone()
        );
    }

    public List<ObjetoRastreadoResponseDTO> obterMeusObjetos(String email) {
    Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    return objetoRastreadoRepository.findByUsuarioId(usuario.getId())
            .stream()
            .map(this::converterObjetoParaDTO)
            .toList();
}

    private ObjetoRastreadoResponseDTO converterObjetoParaDTO(ObjetoRastreado objeto) {
    return new ObjetoRastreadoResponseDTO(
            objeto.getId(),
            objeto.getCodigoRastreio(),
            objeto.getValorFrete(),
            objeto.getValorBem(),
            objeto.getTaxaAlfandegaria(),
            objeto.getOutrosCustos(),
            objeto.getStatus(),
            objeto.getUsuario().getId()
    );
}

public List<EnderecoResponseDTO> obterMeusEnderecos(String email) {
    Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    return enderecoRepository.findByUsuarioId(usuario.getId())
            .stream()
            .map(this::converterEnderecoParaDTO)
            .toList();
}

private EnderecoResponseDTO converterEnderecoParaDTO(Endereco endereco) {
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