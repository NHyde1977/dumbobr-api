package br.com.dumbobr.api.service;

import br.com.dumbobr.api.dto.UsuarioRequestDTO;
import br.com.dumbobr.api.dto.UsuarioResponseDTO;
import br.com.dumbobr.api.model.Endereco;
import br.com.dumbobr.api.model.ObjetoRastreado;
import br.com.dumbobr.api.model.StatusObjeto;
import br.com.dumbobr.api.model.Usuario;
import br.com.dumbobr.api.repository.EnderecoRepository;
import br.com.dumbobr.api.repository.ObjetoRastreadoRepository;
import br.com.dumbobr.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;
import br.com.dumbobr.api.dto.EstatisticasUsuarioResponseDTO;
import br.com.dumbobr.api.model.StatusObjeto;

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
    
    public List<ObjetoRastreadoResponseDTO> listarObjetosDoUsuarioPorStatus(
        Long usuarioId,
        StatusObjeto status
    ) {
    return objetoRastreadoRepository.findByUsuarioIdAndStatus(usuarioId, status)
            .stream()
            .map(this::converterObjetoParaDTO)
            .toList();
    }

    public EstatisticasUsuarioResponseDTO obterEstatisticasDoUsuario(Long usuarioId) {
    return new EstatisticasUsuarioResponseDTO(
            usuarioId,
            objetoRastreadoRepository.findByUsuarioId(usuarioId).size(),
            objetoRastreadoRepository.findByUsuarioIdAndStatus(usuarioId, StatusObjeto.SEM_REGISTRO).size(),
            objetoRastreadoRepository.findByUsuarioIdAndStatus(usuarioId, StatusObjeto.POSTADO).size(),
            objetoRastreadoRepository.findByUsuarioIdAndStatus(usuarioId, StatusObjeto.EM_TRANSITO).size(),
            objetoRastreadoRepository.findByUsuarioIdAndStatus(usuarioId, StatusObjeto.AGUARDANDO_PAGAMENTO).size(),
            objetoRastreadoRepository.findByUsuarioIdAndStatus(usuarioId, StatusObjeto.LIBERADO_PELA_ALFANDEGA).size(),
            objetoRastreadoRepository.findByUsuarioIdAndStatus(usuarioId, StatusObjeto.SAIU_PARA_ENTREGA).size(),
            objetoRastreadoRepository.findByUsuarioIdAndStatus(usuarioId, StatusObjeto.ENTREGUE).size(),
            objetoRastreadoRepository.findByUsuarioIdAndStatus(usuarioId, StatusObjeto.DEVOLVIDO).size()
    );
    }

    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
    Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    return converterParaDTO(usuario);
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO dto) {
    Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    usuario.setNome(dto.getNome());
    usuario.setCpf(dto.getCpf());
    usuario.setEmail(dto.getEmail());
    usuario.setTelefone(dto.getTelefone());

    Usuario usuarioAtualizado = usuarioRepository.save(usuario);

    return converterParaDTO(usuarioAtualizado);
}

public void excluirUsuario(Long id) {
    Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    enderecoRepository.deleteAll(enderecoRepository.findByUsuarioId(id));
    objetoRastreadoRepository.deleteAll(objetoRastreadoRepository.findByUsuarioId(id));

    usuarioRepository.delete(usuario);
}
}