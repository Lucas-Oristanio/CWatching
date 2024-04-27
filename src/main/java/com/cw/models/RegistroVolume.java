package com.cw.models;

public class RegistroVolume {
    private Integer idRegistroVolume;
    private Long volumeDisponivel;
    private Long volumeTotal;
    private String dtHora;
    private String fkVolume;

    public RegistroVolume(Long volumeDisponivel, Long volumeTotal, String fkVolume) {
        this.volumeDisponivel = volumeDisponivel;
        this.volumeTotal = volumeTotal;
        this.fkVolume = fkVolume;
    }

    public RegistroVolume() {
    }

    public Integer getIdRegistroVolume() {
        return idRegistroVolume;
    }

    public void setIdRegistroVolume(Integer idRegistroVolume) {
        this.idRegistroVolume = idRegistroVolume;
    }

    public Long getVolumeDisponivel() {
        return volumeDisponivel;
    }

    public void setVolumeDisponivel(Long volumeDisponivel) {
        this.volumeDisponivel = volumeDisponivel;
    }

    public String getDtHora() {
        return dtHora;
    }

    public void setDtHora(String dtHora) {
        this.dtHora = dtHora;
    }

    public Long getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(Long volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public String getFkVolume() {
        return fkVolume;
    }

    public void setFkVolume(String fkVolume) {
        this.fkVolume = fkVolume;
    }

    @Override
    public String toString() {
        return "RegistroVolume{" +
                "idRegistroVolume=" + idRegistroVolume +
                ", volumeDisponivel=" + volumeDisponivel +
                ", dtHora='" + dtHora + '\'' +
                ", volumeTotal=" + volumeTotal +
                ", fkVolume=" + fkVolume +
                '}';
    }
}
