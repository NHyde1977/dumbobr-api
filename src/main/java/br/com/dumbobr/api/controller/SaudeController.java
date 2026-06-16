package br.com.dumbobr.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


public class SaudeController {
     @GetMapping("/saude")
    public Map<String, String> saude() {
        return Map.of(
                "status", "DumboBR API rodando",
                "application", "dumbobr-api",
                "version", "0.0.1"
        );
    }
}
