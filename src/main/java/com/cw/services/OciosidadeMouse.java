package com.cw.services;

import com.cw.dao.OciosidadeMouseDAO;
import com.cw.models.RegistroOciosidadeMouse;
import com.cw.models.Usuario;

import java.awt.*;

public class OciosidadeMouse {
    private final Boolean DEBUG = false;

    private Integer tempoDecrescenteMs;
    private Integer tempoCrescenteMs;

    private Integer tempoDecrescente;

    private Integer sensibilidadeThreshold;

    private Boolean timerDecrescenteRodando;
    private Boolean mouseMoveu;

    private Usuario usuario;

    public OciosidadeMouse(Usuario usuario) {
        this.timerDecrescenteRodando = false;
        this.tempoCrescenteMs = 0;
        this.usuario = usuario;
    }

    public void iniciar() {
        new Thread(threadMouse).start();
    }

    private Runnable threadMouse = new Runnable() {
        public void run() {
            try {
                // Busca coordenadas do mouse em dois intervalos de tempo
                Point coordAnterior = getCoordenadaMouse();
                Thread.sleep(300);
                Point coordAtual = getCoordenadaMouse();

                // Cálculo para checar se mouse moveu dentro de uma threshold estabelecida com base em suas coordenadas
                int intervaloXMenor = coordAtual.x - sensibilidadeThreshold;
                int intervaloXMaior = coordAtual.x + sensibilidadeThreshold;
                int intervaloYMenor = coordAtual.y - sensibilidadeThreshold;
                int intervaloYMaior = coordAtual.y + sensibilidadeThreshold;

                mouseMoveu = (
                        (coordAnterior.x < intervaloXMenor) ||
                                (coordAnterior.x > intervaloXMaior) ||
                                (coordAnterior.y < intervaloYMenor) ||
                                (coordAnterior.y > intervaloYMaior)
                );


                if (mouseMoveu)
                    tempoDecrescente = tempoDecrescenteMs; // Reseta a contagem regressiva caso o mouse mova

                if (mouseMoveu && !timerDecrescenteRodando) {
                    timerDecrescenteRodando = true;
                    new Thread(threadTimerDecrescente).start(); // Inicia a contagem regressiva
                }

                run();
            } catch (Exception e) {}
        }
    };

    private Runnable threadTimerDecrescente = new Runnable() {
        public void run() {
            try {
                tempoDecrescente -= 100;
                Thread.sleep(100);
                if (DEBUG && tempoDecrescente % 1000 == 0) System.out.println("Falta para ocioso: " + tempoDecrescente);

                if (tempoDecrescente <= 0)
                    throw new RuntimeException();

                run();
            } catch (Exception e) {
                // Contagem regressiva chegou à zero
                timerDecrescenteRodando = false;
                new Thread(threadTimerCrescente).start(); // Inicia a contagem do tempo de ociosidade
            }
        }
    };

    private Runnable threadTimerCrescente = new Runnable() {
        public void run() {
            try {
                tempoCrescenteMs += 100;
                Thread.sleep(100);

                if (DEBUG && tempoCrescenteMs % 1000 == 0) System.out.println("Ocioso: " + tempoCrescenteMs);

                if (mouseMoveu)
                    throw new RuntimeException();

                run();
            } catch (Exception e) {
                // Insere o tempo da ociosidade da vez e reseta o timer
                new OciosidadeMouseDAO().inserirOciosidadeMouse(new RegistroOciosidadeMouse(tempoCrescenteMs, usuario.getIdUsuario()));
                if (DEBUG) System.out.println("Inserido %d segundos".formatted(tempoCrescenteMs));
                tempoCrescenteMs = 0;
            }
        }
    };

    public Point getCoordenadaMouse() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    public void setTempoDecrescenteMs(Integer tempoDecrescenteMs) {
        this.tempoDecrescenteMs = tempoDecrescenteMs;
        this.tempoDecrescente = tempoDecrescenteMs;
    }

    public void setSensibilidadeThreshold(Integer sensibilidadeThreshold) {
        if (sensibilidadeThreshold < 0 || sensibilidadeThreshold > 100) return;
        this.sensibilidadeThreshold = sensibilidadeThreshold;
    }
}
