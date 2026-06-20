package br.com.dumbobr.api.controller;

import br.com.dumbobr.api.dto.ObjetoRastreadoRequestDTO;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;
import br.com.dumbobr.api.service.ObjetoRastreadoService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.dumbobr.api.model.StatusObjeto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@Tag(
    name = "Objetos Rastreados",
    description = "Operações relacionadas aos objetos rastreados do sistema DumboBR"
)
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/objetos")
public class ObjetoRastreadoController {

    private final ObjetoRastreadoService objetoRastreadoService;

    public ObjetoRastreadoController(ObjetoRastreadoService objetoRastreadoService) {
        this.objetoRastreadoService = objetoRastreadoService;
    }

@Operation(
    summary = "Listar objetos rastreados",
    description = "Retorna todos os objetos rastreados cadastrados no sistema."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista de objetos retornada com sucesso")
})
    @GetMapping
    public List<ObjetoRastreadoResponseDTO> listarObjetos() {
        return objetoRastreadoService.listarObjetos();
    }

@Operation(
    summary = "Cadastrar objeto rastreado",
    description = "Cadastra um novo objeto rastreado associado a um usuário."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Objeto rastreado cadastrado com sucesso"),
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário não encontrado")
})
    @PostMapping
    public ObjetoRastreadoResponseDTO cadastrarObjeto(
            @RequestBody @Valid ObjetoRastreadoRequestDTO dto
    ) {
        return objetoRastreadoService.cadastrarObjeto(dto);
    }

@Operation(
        summary = "Listar objetos por status",
        description = "Retorna todos os objetos rastreados que possuem o status informado."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Objetos encontrados com sucesso"),
        @ApiResponse(responseCode = "400", description = "Status inválido")
})
@GetMapping("/status/{status}")
public List<ObjetoRastreadoResponseDTO> listarObjetosPorStatus(
        @PathVariable StatusObjeto status
) {
    return objetoRastreadoService.listarObjetosPorStatus(status);
}

@Operation(
        summary = "Buscar objeto rastreado por ID",
        description = "Retorna os dados de um objeto rastreado específico."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Objeto encontrado"),
        @ApiResponse(responseCode = "400", description = "Objeto rastreado não encontrado")
})
@GetMapping("/{id}")
public ObjetoRastreadoResponseDTO buscarObjetoPorId(@PathVariable Long id) {
    return objetoRastreadoService.buscarObjetoPorId(id);
}

@Operation(
        summary = "Atualizar objeto rastreado",
        description = "Atualiza os dados de um objeto rastreado existente."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Objeto atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos, objeto ou usuário não encontrado")
})
@PutMapping("/{id}")
public ObjetoRastreadoResponseDTO atualizarObjeto(
        @PathVariable Long id,
        @RequestBody @Valid ObjetoRastreadoRequestDTO dto
) {
    return objetoRastreadoService.atualizarObjeto(id, dto);
}

@Operation(
        summary = "Excluir objeto rastreado",
        description = "Remove um objeto rastreado existente do sistema."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Objeto excluído com sucesso"),
        @ApiResponse(responseCode = "400", description = "Objeto não encontrado")
})
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void excluirObjeto(@PathVariable Long id) {
    objetoRastreadoService.excluirObjeto(id);
}
}