package com.cw.models;

public class Sessao {
    private Integer idSessao;
    private Integer fkMaquina;
    private Integer fkUsuario;
    private String dtHoraSessao;

    public Sessao(Integer fkMaquina, Integer fkUsuario) {
        this.fkMaquina = fkMaquina;
        this.fkUsuario = fkUsuario;
    }

    public Sessao() {
    }

    public Integer getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Integer idSessao) {
        this.idSessao = idSessao;
    }

    public Integer getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public String getDtHoraSessao() {
        return dtHoraSessao;
    }

    public void setDtHoraSessao(String dtHoraSessao) {
        this.dtHoraSessao = dtHoraSessao;
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "idSessao=" + idSessao +
                ", fkMaquina=" + fkMaquina +
                ", fkUsuario=" + fkUsuario +
                ", dtHoraSessao='" + dtHoraSessao + '\'' +
                '}';
    }
}
