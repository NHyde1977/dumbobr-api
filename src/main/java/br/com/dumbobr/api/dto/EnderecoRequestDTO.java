package br.com.dumbobr.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public class EnderecoRequestDTO {
    
    @Schema(description = "CEP", example = "22070-011")
    @NotBlank(message = "O CEP é obrigatório")
    private String cep;

    @Schema(description = "Logradouro", example = "Rua ABC")
    @NotBlank(message = "O logradouro é obrigatório")
    private String logradouro;

    @Schema(description = "Número", example = "245")
    @NotBlank(message = "O número é obrigatório")
    private String numero;

    @Schema(description = "Complemento", example = "Apto 302")
    private String complemento;

    @Schema(description = "Bairro", example = "Perdizes")
    @NotBlank(message = "O bairro é obrigatório")
    private String bairro;

    @Schema(description = "Cidade", example = "Sao Paulo")
    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @Schema(description = "Estado", example = "SP")
    @NotBlank(message = "O estado é obrigatório")
    private String estado;

    @Schema(description = "ID do usuário proprietário do endereço", example = "1")
    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;

    public EnderecoRequestDTO() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

      public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
