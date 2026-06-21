package br.com.dumbobr.api.service;

import br.com.dumbobr.api.dto.UsuarioResponseDTO;
import br.com.dumbobr.api.model.Usuario;
import br.com.dumbobr.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;
import br.com.dumbobr.api.model.ObjetoRastreado;
import br.com.dumbobr.api.repository.ObjetoRastreadoRepository;
import br.com.dumbobr.api.dto.EnderecoRequestDTO;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;
import br.com.dumbobr.api.dto.ObjetoRastreadoRequestDTO;
import br.com.dumbobr.api.model.Endereco;
import br.com.dumbobr.api.repository.EnderecoRepository;
import br.com.dumbobr.api.dto.EnderecoRequestDTO;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;
import br.com.dumbobr.api.model.Endereco;

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

public ObjetoRastreadoResponseDTO cadastrarMeuObjeto(
        String email,
        ObjetoRastreadoRequestDTO dto
) {
    Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    ObjetoRastreado objeto = new ObjetoRastreado(
            dto.getCodigoRastreio(),
            dto.getValorFrete(),
            dto.getValorBem(),
            dto.getTaxaAlfandegaria(),
            dto.getOutrosCustos(),
            dto.getStatus(),
            usuario
    );

    ObjetoRastreado objetoSalvo = objetoRastreadoRepository.save(objeto);

    return converterObjetoParaDTO(objetoSalvo);
}

public EnderecoResponseDTO cadastrarMeuEndereco(
        String email,
        EnderecoRequestDTO dto
) {
    Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

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

    return converterEnderecoParaDTO(enderecoSalvo);
}
}