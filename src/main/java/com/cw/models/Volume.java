package com.cw.models;

public class Volume {
    private String UUID;
    private String nome;
    private String pontoMontagem;
    private Integer fkMaquina;

    public Volume(String UUID, String nome, String pontoMontagem, Integer fkMaquina) {
        this.UUID = UUID;
        this.nome = nome;
        this.pontoMontagem = pontoMontagem;
        this.fkMaquina = fkMaquina;
    }

    public Volume() {
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPontoMontagem() {
        return pontoMontagem;
    }

    public void setPontoMontagem(String pontoMontagem) {
        this.pontoMontagem = pontoMontagem;
    }

    public Integer getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    @Override
    public String toString() {
        return "Volume{" +
                "UUID=" + UUID +
                ", nome='" + nome + '\'' +
                ", pontoMontagem='" + pontoMontagem + '\'' +
                ", fkMaquina=" + fkMaquina +
                '}';
    }
}
