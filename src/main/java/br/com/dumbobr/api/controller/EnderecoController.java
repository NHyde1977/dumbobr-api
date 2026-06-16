package br.com.dumbobr.api.controller;

import br.com.dumbobr.api.dto.EnderecoRequestDTO;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;
import br.com.dumbobr.api.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
    name = "Endereços",
    description = "Operações relacionadas aos endereços dos usuários"
)
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

@Operation(
    summary = "Listar endereços",
    description = "Retorna todos os endereços cadastrados no sistema."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso")
})
    @GetMapping
    public List<EnderecoResponseDTO> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

@Operation(
    summary = "Cadastrar endereço",
    description = "Cadastra um novo endereço vinculado a um usuário."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso"),
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário não encontrado")
})
    @PostMapping
    public EnderecoResponseDTO cadastrarEndereco(
            @RequestBody @Valid EnderecoRequestDTO dto
    ) {
        return enderecoService.cadastrarEndereco(dto);
    }
}