package br.com.dumbobr.api.service;

import br.com.dumbobr.api.dto.ObjetoRastreadoRequestDTO;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;
import br.com.dumbobr.api.model.ObjetoRastreado;
import br.com.dumbobr.api.model.Usuario;
import br.com.dumbobr.api.repository.ObjetoRastreadoRepository;
import br.com.dumbobr.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

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
}