package br.com.dumbobr.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class ObjetoRastreado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoRastreio;
    private BigDecimal valorFrete;
    private BigDecimal valorBem;
    private BigDecimal taxaAlfandegaria;
    private BigDecimal outrosCustos;
    private StatusObjeto status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public ObjetoRastreado() {
    }

    public ObjetoRastreado(
            String codigoRastreio,
            BigDecimal valorFrete,
            BigDecimal valorBem,
            BigDecimal taxaAlfandegaria,
            BigDecimal outrosCustos,
            StatusObjeto status,
            Usuario usuario
    ) {
        this.codigoRastreio = codigoRastreio;
        this.valorFrete = valorFrete;
        this.valorBem = valorBem;
        this.taxaAlfandegaria = taxaAlfandegaria;
        this.outrosCustos = outrosCustos;
        this.status = status;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public void setValorBem(BigDecimal valorBem) {
        this.valorBem = valorBem;
    }

    public void setTaxaAlfandegaria(BigDecimal taxaAlfandegaria) {
        this.taxaAlfandegaria = taxaAlfandegaria;
    }

    public void setOutrosCustos(BigDecimal outrosCustos) {
        this.outrosCustos = outrosCustos;
    }

    public void setStatus(StatusObjeto status) {
        this.status = status;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}