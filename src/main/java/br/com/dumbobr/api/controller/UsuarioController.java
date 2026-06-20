package br.com.dumbobr.api.controller;

import br.com.dumbobr.api.dto.UsuarioRequestDTO;
import br.com.dumbobr.api.dto.UsuarioResponseDTO;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;
import br.com.dumbobr.api.service.UsuarioService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import br.com.dumbobr.api.model.StatusObjeto;
import br.com.dumbobr.api.dto.EstatisticasUsuarioResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

@Tag(
    name = "Usuários",
    description = "Operações relacionadas aos usuários do sistema DumboBR"
)
@SecurityRequirement(name = "bearerAuth")
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

@Operation(
        summary = "Listar objetos do usuário por status",
        description = "Retorna os objetos rastreados de um usuário filtrados pelo status informado."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Objetos encontrados com sucesso"),
        @ApiResponse(responseCode = "400", description = "Status inválido")
})
@GetMapping("/{id}/objetos/status/{status}")
public List<ObjetoRastreadoResponseDTO> listarObjetosDoUsuarioPorStatus(
        @PathVariable Long id,
        @PathVariable StatusObjeto status
) {
    return usuarioService.listarObjetosDoUsuarioPorStatus(id, status);
}

@Operation(
        summary = "Obter estatísticas do usuário",
        description = "Retorna a quantidade de objetos rastreados do usuário agrupados por status."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estatísticas retornadas com sucesso")
})
@GetMapping("/{id}/estatisticas")
public EstatisticasUsuarioResponseDTO obterEstatisticasDoUsuario(@PathVariable Long id) {
    return usuarioService.obterEstatisticasDoUsuario(id);
}

@Operation(
        summary = "Buscar usuário por ID",
        description = "Retorna os dados de um usuário específico."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
})
@GetMapping("/{id}")
public UsuarioResponseDTO buscarUsuarioPorId(@PathVariable Long id) {
    return usuarioService.buscarUsuarioPorId(id);
}

@Operation(
        summary = "Atualizar usuário",
        description = "Atualiza os dados cadastrais de um usuário existente."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário não encontrado")
})
@PutMapping("/{id}")
public UsuarioResponseDTO atualizarUsuario(
        @PathVariable Long id,
        @RequestBody @Valid UsuarioRequestDTO dto
) {
    return usuarioService.atualizarUsuario(id, dto);
}

@Operation(
        summary = "Excluir usuário",
        description = "Remove um usuário e todos os seus endereços e objetos rastreados."
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
        @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
})
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void excluirUsuario(@PathVariable Long id) {
    usuarioService.excluirUsuario(id);
}
}