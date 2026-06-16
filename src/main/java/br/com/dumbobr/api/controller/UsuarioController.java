package br.com.dumbobr.api.controller;

import br.com.dumbobr.api.dto.UsuarioRequestDTO;
import br.com.dumbobr.api.dto.UsuarioResponseDTO;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;
import br.com.dumbobr.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

@Tag(
    name = "Usuários",
    description = "Operações relacionadas aos usuários do sistema DumboBR"
)
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

@Operation(
    summary = "Listar usuários",
    description = "Retorna todos os usuários cadastrados no sistema."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
})

    @GetMapping
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

@Operation(
    summary = "Cadastrar usuário",
    description = "Cadastra um novo usuário no sistema."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
})
    @PostMapping
    public UsuarioResponseDTO cadastrarUsuario(
            @RequestBody @Valid UsuarioRequestDTO dto
    ) {
        return usuarioService.cadastrarUsuario(dto);
    }

@Operation(
    summary = "Listar endereços do usuário",
    description = "Retorna todos os endereços cadastrados para um usuário."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Endereços encontrados"),
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
})
    @GetMapping("/{id}/enderecos")
    public List<EnderecoResponseDTO> listarEnderecosDoUsuario(@PathVariable Long id) {
        return usuarioService.listarEnderecosDoUsuario(id);
    }

@Operation(
    summary = "Listar objetos rastreados do usuário",
    description = "Retorna todos os objetos rastreados pertencentes ao usuário."
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Objetos encontrados"),
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
})
    @GetMapping("/{id}/objetos")
    public List<ObjetoRastreadoResponseDTO> listarObjetosDoUsuario(@PathVariable Long id) {
        return usuarioService.listarObjetosDoUsuario(id);
    }
}