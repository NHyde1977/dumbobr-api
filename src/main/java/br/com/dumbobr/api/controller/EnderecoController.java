package br.com.dumbobr.api.controller;

import br.com.dumbobr.api.dto.EnderecoRequestDTO;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;
import br.com.dumbobr.api.service.EnderecoService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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

@Operation(
        summary = "Buscar endereço por ID",
        description = "Retorna os dados de um endereço específico."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereço encontrado"),
        @ApiResponse(responseCode = "400", description = "Endereço não encontrado")
})
@GetMapping("/{id}")
public EnderecoResponseDTO buscarEnderecoPorId(@PathVariable Long id) {
    return enderecoService.buscarEnderecoPorId(id);
}

@Operation(
        summary = "Atualizar endereço",
        description = "Atualiza os dados de um endereço existente."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos, endereço ou usuário não encontrado")
})
@PutMapping("/{id}")
public EnderecoResponseDTO atualizarEndereco(
        @PathVariable Long id,
        @RequestBody @Valid EnderecoRequestDTO dto
) {
    return enderecoService.atualizarEndereco(id, dto);
}

@Operation(
        summary = "Excluir endereço",
        description = "Remove um endereço existente do sistema."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Endereço excluído com sucesso"),
        @ApiResponse(responseCode = "400", description = "Endereço não encontrado")
})
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void excluirEndereco(@PathVariable Long id) {
    enderecoService.excluirEndereco(id);
}
}