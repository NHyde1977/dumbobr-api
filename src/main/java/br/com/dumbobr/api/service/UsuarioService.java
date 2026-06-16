package br.com.dumbobr.api.service;

import br.com.dumbobr.api.dto.UsuarioRequestDTO;
import br.com.dumbobr.api.dto.UsuarioResponseDTO;
import br.com.dumbobr.api.model.Endereco;
import br.com.dumbobr.api.model.ObjetoRastreado;
import br.com.dumbobr.api.model.Usuario;
import br.com.dumbobr.api.repository.EnderecoRepository;
import br.com.dumbobr.api.repository.ObjetoRastreadoRepository;
import br.com.dumbobr.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final ObjetoRastreadoRepository objetoRastreadoRepository;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            EnderecoRepository enderecoRepository,
            ObjetoRastreadoRepository objetoRastreadoRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
        this.objetoRastreadoRepository = objetoRastreadoRepository;
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

    public List<EnderecoResponseDTO> listarEnderecosDoUsuario(Long usuarioId) {
    return enderecoRepository.findByUsuarioId(usuarioId)
            .stream()
            .map(this::converterEnderecoParaDTO)
            .toList();
    }

    public List<ObjetoRastreadoResponseDTO> listarObjetosDoUsuario(Long usuarioId) {
        return objetoRastreadoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::converterObjetoParaDTO)
                .toList();
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
}