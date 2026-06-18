package br.com.dumbobr.api.dto;

public class EstatisticasUsuarioResponseDTO {

    private Long usuarioId;
    private long totalObjetos;
    private long semRegistro;
    private long postados;
    private long emTransito;
    private long aguardandoPagamento;
    private long liberadosPelaAlfandega;
    private long sairamParaEntrega;
    private long entregues;
    private long devolvidos;

    public EstatisticasUsuarioResponseDTO(
            Long usuarioId,
            long totalObjetos,
            long semRegistro,
            long postados,
            long emTransito,
            long aguardandoPagamento,
            long liberadosPelaAlfandega,
            long sairamParaEntrega,
            long entregues,
            long devolvidos
    ) {
        this.usuarioId = usuarioId;
        this.totalObjetos = totalObjetos;
        this.semRegistro = semRegistro;
        this.postados = postados;
        this.emTransito = emTransito;
        this.aguardandoPagamento = aguardandoPagamento;
        this.liberadosPelaAlfandega = liberadosPelaAlfandega;
        this.sairamParaEntrega = sairamParaEntrega;
        this.entregues = entregues;
        this.devolvidos = devolvidos;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public long getTotalObjetos() {
        return totalObjetos;
    }

    public long getSemRegistro() {
        return semRegistro;
    }

    public long getPostados() {
        return postados;
    }

    public long getEmTransito() {
        return emTransito;
    }

    public long getAguardandoPagamento() {
        return aguardandoPagamento;
    }

    public long getLiberadosPelaAlfandega() {
        return liberadosPelaAlfandega;
    }

    public long getSairamParaEntrega() {
        return sairamParaEntrega;
    }

    public long getEntregues() {
        return entregues;
    }

    public long getDevolvidos() {
        return devolvidos;
    }
}