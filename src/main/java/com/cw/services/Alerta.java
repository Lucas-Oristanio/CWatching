package com.cw.services;

import com.cw.models.ParametroAlerta;
import com.cw.models.Registro;
import com.cw.models.RegistroVolume;
import com.github.britooo.looca.api.core.Looca;

public class Alerta {
    private ParametroAlerta parametro;
    Boolean alerta = false;

    Looca looca = new Looca();

    public Alerta(ParametroAlerta parametro) {
        this.parametro = parametro;
    }

    public Boolean verificarAlerta(Registro r) {
        alerta = false;

        Double ram = (double)r.getUsoRam()/(double)(looca.getMemoria().getTotal())*100;
        if (ram > parametro.getMaxRam()) {
            System.out.println("Alerta RAM: %.2f".formatted(ram));
            alerta = true;
        }

        if (r.getUsoCpu() > parametro.getMaxCpu()) {
            System.out.println("Alerta CPU: %.2f".formatted(r.getUsoCpu()));
            alerta = true;
        }

        return alerta;
    }

    public void verificarAlerta(RegistroVolume v) {
        Double disponivel = (double)v.getVolumeDisponivel()/(double)(v.getVolumeTotal())*100;
        if (disponivel < (100.0 - parametro.getMaxVolume())) {
            System.out.println("Alerta Volume %s: %.2f".formatted(v.getFkVolume(), disponivel));
        }
    }
}
