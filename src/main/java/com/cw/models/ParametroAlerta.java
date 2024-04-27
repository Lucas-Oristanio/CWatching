package com.cw.models;

public class ParametroAlerta {
    private Integer idParametro;
    private Double maxCpu;
    private Double maxRam;
    private Double maxVolume;
    private Integer sensibilidadeMouse;
    private Integer timerMouseMs;
    private Integer intervaloRegistroMs;
    private Integer intervaloVolumeMs;

    public ParametroAlerta(Integer idParametro, Double maxCpu, Double maxRam, Double maxVolume, Integer sensibilidadeMouse, Integer timerMouseMs, Integer intervaloRegistroMs, Integer intervaloVolumeMs) {
        this.idParametro = idParametro;
        this.maxCpu = maxCpu;
        this.maxRam = maxRam;
        this.maxVolume = maxVolume;
        this.sensibilidadeMouse = sensibilidadeMouse;
        this.timerMouseMs = timerMouseMs;
        this.intervaloRegistroMs = intervaloRegistroMs;
        this.intervaloVolumeMs = intervaloVolumeMs;
    }

    public ParametroAlerta() {
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public Double getMaxCpu() {
        return maxCpu;
    }

    public void setMaxCpu(Double maxCpu) {
        this.maxCpu = maxCpu;
    }

    public Double getMaxRam() {
        return maxRam;
    }

    public void setMaxRam(Double maxRam) {
        this.maxRam = maxRam;
    }

    public Double getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(Double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public Integer getSensibilidadeMouse() {
        return sensibilidadeMouse;
    }

    public void setSensibilidadeMouse(Integer sensibilidadeMouse) {
        this.sensibilidadeMouse = sensibilidadeMouse;
    }

    public Integer getTimerMouseMs() {
        return timerMouseMs;
    }

    public void setTimerMouseMs(Integer timerMouseMs) {
        this.timerMouseMs = timerMouseMs;
    }

    public Integer getIntervaloRegistroMs() {
        return intervaloRegistroMs;
    }

    public void setIntervaloRegistroMs(Integer intervaloRegistroMs) {
        this.intervaloRegistroMs = intervaloRegistroMs;
    }

    public Integer getIntervaloVolumeMs() {
        return intervaloVolumeMs;
    }

    public void setIntervaloVolumeMs(Integer intervaloVolumeMs) {
        this.intervaloVolumeMs = intervaloVolumeMs;
    }

    @Override
    public String toString() {
        return "ParametroAlerta{" +
                "idParametro=" + idParametro +
                ", maxCpu=" + maxCpu +
                ", maxRam=" + maxRam +
                ", maxVolume=" + maxVolume +
                ", sensibilidadeMouse=" + sensibilidadeMouse +
                ", timerMouseMs=" + timerMouseMs +
                ", intervaloRegistroMs=" + intervaloRegistroMs +
                ", intervaloVolumeMs=" + intervaloVolumeMs +
                '}';
    }
}
