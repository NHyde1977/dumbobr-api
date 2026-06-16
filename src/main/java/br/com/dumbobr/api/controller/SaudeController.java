package br.com.dumbobr.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.Map;


@Tag(name = "Saúde", description = "Endpoint para verificar se a API está em execução")
@RestController
public class SaudeController {

    @Operation(summary = "Verificar status da API")
    @GetMapping("/saude")
    public Map<String, Object> saude() {
        return Map.of(
                "status", "UP",
                "aplicacao", "dumbobr-api",
                "versao", "0.0.1"
        );
    }
}