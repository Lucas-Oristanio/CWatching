package com.cw.models;

import org.h2.util.DateTimeUtils;

public class RegistroOciosidadeMouse {
    private int idTempoOciosidade;
    private String dtHoraRegistro;
    private int tempoRegistroSeg;
    private int fkUsuario;

    public RegistroOciosidadeMouse(int tempoRegistroSeg, int fkUsuario) {
        this.tempoRegistroSeg = tempoRegistroSeg;
        this.fkUsuario = fkUsuario;
    }

    public RegistroOciosidadeMouse() {
    }

    public int getTempoRegistroSeg() {
        return tempoRegistroSeg;
    }

    public int getIdTempoOciosidade() {
        return idTempoOciosidade;
    }

    public void setIdTempoOciosidade(int idTempoOciosidade) {
        this.idTempoOciosidade = idTempoOciosidade;
    }

    public String getDtHoraRegistro() {
        return dtHoraRegistro;
    }

    public void setDtHoraRegistro(String dtHoraRegistro) {
        this.dtHoraRegistro = dtHoraRegistro;
    }

    public void setTempoRegistroSeg(int tempoRegistroSeg) {
        this.tempoRegistroSeg = tempoRegistroSeg;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    @Override
    public String toString() {
        return "RegistroOciosidadeMouse{" +
                "idTempoOciosidade=" + idTempoOciosidade +
                ", dtHoraRegistro='" + dtHoraRegistro + '\'' +
                ", tempoRegistroSeg=" + tempoRegistroSeg +
                ", fkUsuario=" + fkUsuario +
                '}';
    }
}
