package br.com.dumbobr.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioRequestDTO {

    @Schema(
            description = "Nome completo do usuário",
            example = "Bob Harko"
    )
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

     @Schema(
            description = "CPF do usuário",
            example = "123.456.789-00"
    )
    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @Schema(
            description = "Email do usuário",
            example = "bob@email.com"
    )
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

     @Schema(
            description = "Telefone do usuário",
            example = "(21) 99999-9999"
    )
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    public UsuarioRequestDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}