package br.com.dumbobr.api.dto;

import java.math.BigDecimal;

import br.com.dumbobr.api.model.StatusObjeto;

public class ObjetoRastreadoResponseDTO {

    private Long id;
    private String codigoRastreio;
    private BigDecimal valorFrete;
    private BigDecimal valorBem;
    private BigDecimal taxaAlfandegaria;
    private BigDecimal outrosCustos;
    private StatusObjeto status;
    private Long usuarioId;

    public ObjetoRastreadoResponseDTO(
            Long id,
            String codigoRastreio,
            BigDecimal valorFrete,
            BigDecimal valorBem,
            BigDecimal taxaAlfandegaria,
            BigDecimal outrosCustos,
            StatusObjeto status,
            Long usuarioId
    ) {
        this.id = id;
        this.codigoRastreio = codigoRastreio;
        this.valorFrete = valorFrete;
        this.valorBem = valorBem;
        this.taxaAlfandegaria = taxaAlfandegaria;
        this.outrosCustos = outrosCustos;
        this.status = status;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public BigDecimal getValorBem() {
        return valorBem;
    }

    public BigDecimal getTaxaAlfandegaria() {
        return taxaAlfandegaria;
    }

    public BigDecimal getOutrosCustos() {
        return outrosCustos;
    }

    public StatusObjeto getStatus() {
        return status;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}