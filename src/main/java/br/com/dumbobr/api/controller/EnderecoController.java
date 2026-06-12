package br.com.dumbobr.api.controller;

import br.com.dumbobr.api.dto.EnderecoRequestDTO;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;
import br.com.dumbobr.api.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public List<EnderecoResponseDTO> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

    @PostMapping
    public EnderecoResponseDTO cadastrarEndereco(
            @RequestBody @Valid EnderecoRequestDTO dto
    ) {
        return enderecoService.cadastrarEndereco(dto);
    }
}