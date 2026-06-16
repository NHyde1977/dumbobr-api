package br.com.dumbobr.api.controller;

import br.com.dumbobr.api.dto.ObjetoRastreadoRequestDTO;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;
import br.com.dumbobr.api.service.ObjetoRastreadoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
    name = "Objetos Rastreados",
    description = "Operações relacionadas aos objetos rastreados do sistema DumboBR"
)
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
}