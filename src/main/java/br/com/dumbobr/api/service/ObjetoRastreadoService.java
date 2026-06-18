package br.com.dumbobr.api.service;

import br.com.dumbobr.api.dto.ObjetoRastreadoRequestDTO;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;
import br.com.dumbobr.api.model.ObjetoRastreado;
import br.com.dumbobr.api.model.Usuario;
import br.com.dumbobr.api.repository.ObjetoRastreadoRepository;
import br.com.dumbobr.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import br.com.dumbobr.api.model.StatusObjeto;

import java.util.List;

@Service
public class ObjetoRastreadoService {

    private final ObjetoRastreadoRepository objetoRastreadoRepository;
    private final UsuarioRepository usuarioRepository;

    public ObjetoRastreadoService(
            ObjetoRastreadoRepository objetoRastreadoRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.objetoRastreadoRepository = objetoRastreadoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ObjetoRastreadoResponseDTO> listarObjetos() {
        return objetoRastreadoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

        public List<ObjetoRastreadoResponseDTO> listarObjetosPorStatus(StatusObjeto status) {
    return objetoRastreadoRepository.findByStatus(status)
            .stream()
            .map(this::converterParaDTO)
            .toList();
    }

    public ObjetoRastreadoResponseDTO cadastrarObjeto(ObjetoRastreadoRequestDTO dto) {
        Usuario usuario = usuarioRepository
                .findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ObjetoRastreado objetoRastreado = new ObjetoRastreado(
                dto.getCodigoRastreio(),
                dto.getValorFrete(),
                dto.getValorBem(),
                dto.getTaxaAlfandegaria(),
                dto.getOutrosCustos(),
                dto.getStatus(),
                usuario
        );

        ObjetoRastreado objetoSalvo = objetoRastreadoRepository.save(objetoRastreado);

        return converterParaDTO(objetoSalvo);
    }

    private ObjetoRastreadoResponseDTO converterParaDTO(ObjetoRastreado objeto) {
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

    public ObjetoRastreadoResponseDTO buscarObjetoPorId(Long id) {
    ObjetoRastreado objeto = objetoRastreadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Objeto rastreado não encontrado"));

    return converterParaDTO(objeto);
    }

    public ObjetoRastreadoResponseDTO atualizarObjeto(Long id, ObjetoRastreadoRequestDTO dto) {
    ObjetoRastreado objeto = objetoRastreadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Objeto rastreado não encontrado"));

    Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    objeto.setCodigoRastreio(dto.getCodigoRastreio());
    objeto.setValorFrete(dto.getValorFrete());
    objeto.setValorBem(dto.getValorBem());
    objeto.setTaxaAlfandegaria(dto.getTaxaAlfandegaria());
    objeto.setOutrosCustos(dto.getOutrosCustos());
    objeto.setStatus(dto.getStatus());
    objeto.setUsuario(usuario);

    ObjetoRastreado objetoAtualizado = objetoRastreadoRepository.save(objeto);

    return converterParaDTO(objetoAtualizado);
}

public void excluirObjeto(Long id) {
    ObjetoRastreado objeto = objetoRastreadoRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Objeto rastreado não encontrado"));

    objetoRastreadoRepository.delete(objeto);
}
}