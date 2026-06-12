package br.com.dumbobr.api.exception;

import java.util.List;

public class ErroValidacaoResponse {

    private int status;
    private String erro;
    private List<String> mensagens;

    public ErroValidacaoResponse(int status, String erro, List<String> mensagens) {
        this.status = status;
        this.erro = erro;
        this.mensagens = mensagens;
    }

    public int getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public List<String> getMensagens() {
        return mensagens;
    }
}