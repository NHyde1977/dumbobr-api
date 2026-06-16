package br.com.dumbobr.api.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import br.com.dumbobr.api.model.StatusObjeto;

public class ObjetoRastreadoRequestDTO {

    @Schema(
            description = "Código de rastreio do objeto",
            example = "LB123456789BR"
    )
    @NotBlank(message = "O código de rastreio é obrigatório")
    private String codigoRastreio;

    @Schema(
            description = "Valor do frete",
            example = "35.90"
    )
    @NotNull(message = "O valor do frete é obrigatório")
    @PositiveOrZero(message = "O valor do frete não pode ser negativo")
    private BigDecimal valorFrete;

    @Schema(
            description = "Valor do bem adquirido",
            example = "250.00"
    )
    @NotNull(message = "O valor do bem é obrigatório")
    @PositiveOrZero(message = "O valor do bem não pode ser negativo")
    private BigDecimal valorBem;

    @Schema(
            description = "Taxa alfandegária",
            example = "60.00"
    )
    @PositiveOrZero(message = "A taxa alfandegária não pode ser negativa")
    private BigDecimal taxaAlfandegaria;

    @Schema(
            description = "Outros custos",
            example = "12.50"
    )
    @PositiveOrZero(message = "Outros custos não podem ser negativos")
    private BigDecimal outrosCustos;

    @Schema(
            description = "Status atual do objeto",
            example = "EM_TRANSITO"
    )
    @NotNull(message = "O status é obrigatório")
    private StatusObjeto status;

    @Schema(
            description = "ID do usuário dono do objeto",
            example = "1"
    )
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

    public StatusObjeto getStatus() {
    return status;
}

    public void setStatus(StatusObjeto status) {
    this.status = status;
}

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}