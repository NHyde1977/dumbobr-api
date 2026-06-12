package br.com.dumbobr.api.controller;

import br.com.dumbobr.api.dto.ObjetoRastreadoRequestDTO;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;
import br.com.dumbobr.api.service.ObjetoRastreadoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objetos")
public class ObjetoRastreadoController {

    private final ObjetoRastreadoService objetoRastreadoService;

    public ObjetoRastreadoController(ObjetoRastreadoService objetoRastreadoService) {
        this.objetoRastreadoService = objetoRastreadoService;
    }

    @GetMapping
    public List<ObjetoRastreadoResponseDTO> listarObjetos() {
        return objetoRastreadoService.listarObjetos();
    }

    @PostMapping
    public ObjetoRastreadoResponseDTO cadastrarObjeto(
            @RequestBody @Valid ObjetoRastreadoRequestDTO dto
    ) {
        return objetoRastreadoService.cadastrarObjeto(dto);
    }
}