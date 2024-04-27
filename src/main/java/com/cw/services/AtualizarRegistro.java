package com.cw.services;

import com.cw.dao.ProcessoDAO;
import com.cw.dao.RegistroDAO;
import com.cw.models.ParametroAlerta;
import com.cw.models.Processo;
import com.cw.models.Registro;
import com.cw.models.Sessao;
import com.github.britooo.looca.api.core.Looca;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class AtualizarRegistro extends TimerTask {
    private Sessao sessao;
    private Alerta alerta;

    private Looca looca = new Looca();
    private SystemInfo oshi = new SystemInfo();

    private RegistroDAO registroDAO = new RegistroDAO();
    private ProcessoDAO processoDAO = new ProcessoDAO();

    public AtualizarRegistro(Sessao sessao, Alerta alerta) {
        this.sessao = sessao;
        this.alerta = alerta;
    }

    public void run() {
        try {
            Registro registro = new Registro(
                    looca.getProcessador().getUso()*10,
                    looca.getMemoria().getEmUso(),
                    looca.getMemoria().getDisponivel(),
                    sessao.getIdSessao());

            if (alerta.verificarAlerta(registro)) registrarProcessos();

            registroDAO.inserirRegistro(registro);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void registrarProcessos() {
        Registro ultimoRegistro = registroDAO.buscarUltimoRegistroPorSessao(sessao);

        for (OSProcess processo : oshi.getOperatingSystem().getProcesses()) {
            if ((!processo.getPath().contains("C:\\Windows\\System32\\") && !processo.getPath().isEmpty())) {
                Processo p = new Processo(
                        processo.getName(),
                        processo.getPath(),
                        processo.getResidentSetSize(),
                        ultimoRegistro.getIdRegistro());

                processoDAO.inserirProcesso(p);
            }
        }
    }
}

