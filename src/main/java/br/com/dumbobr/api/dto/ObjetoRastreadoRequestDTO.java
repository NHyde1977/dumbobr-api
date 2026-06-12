package br.com.dumbobr.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ObjetoRastreadoRequestDTO {
    
    @NotBlank(message = "O código de rastreio é obrigatório")
    private String codigoRastreio;

    @NotNull(message = "O valor do frete é obrigatório")
    @PositiveOrZero(message = "O valor do frete não pode ser negativo")
    private BigDecimal valorFrete;

    @NotNull(message = "O valor do bem é obrigatório")
    @PositiveOrZero(message = "O valor do bem não pode ser negativo")
    private BigDecimal valorBem;

    @PositiveOrZero(message = "A taxa alfandegária não pode ser negativa")
    private BigDecimal taxaAlfandegaria;

    @PositiveOrZero(message = "Outros custos não podem ser negativos")
    private BigDecimal outrosCustos;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;

    public ObjetoRastreadoRequestDTO() {
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorBem() {
        return valorBem;
    }

    public void setValorBem(BigDecimal valorBem) {
        this.valorBem = valorBem;
    }

    public BigDecimal getTaxaAlfandegaria() {
        return taxaAlfandegaria;
    }

    public void setTaxaAlfandegaria(BigDecimal taxaAlfandegaria) {
        this.taxaAlfandegaria = taxaAlfandegaria;
    }

    public BigDecimal getOutrosCustos() {
        return outrosCustos;
    }

    public void setOutrosCustos(BigDecimal outrosCustos) {
        this.outrosCustos = outrosCustos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
