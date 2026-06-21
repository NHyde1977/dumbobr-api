package br.com.dumbobr.api.controller;

import br.com.dumbobr.api.dto.UsuarioResponseDTO;
import br.com.dumbobr.api.service.MeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.dumbobr.api.dto.ObjetoRastreadoResponseDTO;
import br.com.dumbobr.api.dto.EnderecoResponseDTO;

import java.util.List;

@Tag(
        name = "Minha Conta",
        description = "Endpoints relacionados ao usuário autenticado"
)
@SecurityRequirement(name = "bearerAuth")
@RestController
public class MeController {

    private final MeService meService;

    public MeController(MeService meService) {
        this.meService = meService;
    }

    @Operation(
            summary = "Obter dados do usuário autenticado",
            description = "Retorna os dados do usuário logado com base no token JWT."
    )
    @GetMapping("/me")
    public UsuarioResponseDTO obterUsuarioLogado(Authentication authentication) {
        return meService.obterUsuarioLogado(authentication.getName());
    }

    @Operation(
        summary = "Listar objetos do usuário autenticado",
        description = "Retorna todos os objetos rastreados pertencentes ao usuário logado."
    )
    @GetMapping("/me/objetos")
    public List<ObjetoRastreadoResponseDTO> obterMeusObjetos(Authentication authentication) {
        return meService.obterMeusObjetos(authentication.getName());    
    }

    @Operation(
        summary = "Listar endereços do usuário autenticado",
        description = "Retorna todos os endereços pertencentes ao usuário logado."
    )
    @GetMapping("/me/enderecos")
    public List<EnderecoResponseDTO> obterMeusEnderecos(Authentication authentication) {
        return meService.obterMeusEnderecos(authentication.getName());
    }
}