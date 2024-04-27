package com.cw;

import com.cw.dao.*;
import com.cw.database.CriarTabelas;
import com.cw.database.PopularTabelas;
import com.cw.gpt.Gpt;
import com.cw.models.*;
import com.cw.services.*;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.processos.Processo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class MainCW {

    public static void main(String[] args) throws InterruptedException, IOException {
        // Buscar hostname da máquina atual
        String hostname = new Looca().getRede().getParametros().getHostName();

        UsuarioDAO userDao = new UsuarioDAO();
        MaquinaDAO maquinaDAO = new MaquinaDAO();
        SessaoDAO sessaoDAO = new SessaoDAO();
        ParametroAlertaDAO parametroAlertaDAO = new ParametroAlertaDAO();

        System.out.println("""                                                                      
                   ______           __           _       __      __       __ \s
                  / ____/__  ____  / /____  ____| |     / /___ _/ /______/ /_\s
                 / /   / _ \\/ __ \\/ __/ _ \\/ ___/ | /| / / __ `/ __/ ___/ __ \\
                / /___/  __/ / / / /_/  __/ /   | |/ |/ / /_/ / /_/ /__/ / / /
                \\____/\\___/_/ /_/\\__/\\___/_/    |__/|__/\\__,_/\\__/\\___/_/ /_/\s
                                                                             \s                                                                         
                """);

        CriarTabelas.criarTabelas();
        PopularTabelas.popularTabelas();

        // Loop para interação com usuário (login)
        Boolean continuar;
        do {
            Scanner leitor = new Scanner(System.in);

            System.out.print("Usuário: ");
            String username = leitor.next();

            System.out.print("Senha: ");
            String senha = leitor.next();

            // Autentica o login
            if (userDao.autenticarLogin(username, senha)) {
                // Usuário está logado

                Empresa empresa = userDao.buscarEmpresaPorUsername(username);

                ParametroAlerta parametroAlertaAtual = parametroAlertaDAO.buscarParametroAlertaPorEmpresa(empresa);
                System.out.println(parametroAlertaAtual);

                // Cadastra a máquina atual caso ela não esteja no banco
                RegistrarMaquina registrarMaquina = new RegistrarMaquina();
                registrarMaquina.registrarMaquinaSeNaoExiste(empresa);

                // Busca objetos usuário e máquina para ser registrada a sessão criada
                Usuario usuario = userDao.buscarUsuarioPorUsername(username);
                Maquina maquina = maquinaDAO.buscarMaquinaPorHostnameEEmpresa(hostname, empresa);

                // Registra a sessão criada ao logar
                sessaoDAO.registrarSessao(maquina.getIdMaquina(), usuario.getIdUsuario());
                Sessao sessaoAtual = sessaoDAO.buscarUltimaSessaoPorMaquina(maquina.getIdMaquina());

                System.out.println("Login com sucesso. Iniciando captura de dados...");

                Alerta alerta = new Alerta(parametroAlertaAtual);

                // Inicializa timer para coleta de dados de CPU e RAM
                Timer atualizarRegistro = new Timer();
                atualizarRegistro.schedule(new AtualizarRegistro(sessaoAtual, alerta), 0, parametroAlertaAtual.getIntervaloRegistroMs());

                // Inicializa timer para coleta de dados de volumes
                Timer atualizarVolume = new Timer();
                atualizarVolume.schedule(new AtualizarRegistroVolume(alerta), 0, parametroAlertaAtual.getIntervaloVolumeMs());

                // Inicializa o monitoramento de ociosidade de mouse do usuário
                OciosidadeMouse ociosidadeMouse = new OciosidadeMouse(usuario);
                ociosidadeMouse.setTempoDecrescenteMs(parametroAlertaAtual.getTimerMouseMs());
                ociosidadeMouse.setSensibilidadeThreshold(parametroAlertaAtual.getSensibilidadeMouse());
                ociosidadeMouse.iniciar();

                continuar = false;
            } else {
                System.out.println("Login inválido. Tentar novamente? Y/N");

                continuar = leitor.next().equalsIgnoreCase("Y");
            }

        } while (continuar);

        /* Chat GPT */
        List<String> listaNativos = new ArrayList<>();
        File whiteList = new File("C:\\Users\\lucas\\Desktop\\CWatching\\src\\main\\java\\com\\cw\\gpt\\Whitelist.txt");
        Scanner lerWhiteList = new Scanner(whiteList);

        while(lerWhiteList.hasNextLine()){
            listaNativos.add(lerWhiteList.nextLine()); // Vamos ignorar o processo caso ele seja nativo do windows para não consumir demais a API
        }

        Gpt chat = new Gpt();
        Looca LoocaProcess = new Looca();
        List<Processo> listaProcessos = LoocaProcess.getGrupoDeProcessos().getProcessos();


        while(true){
            String res="";
            for(Processo p : listaProcessos){
                Boolean processoNativo = false;

                for(String pn : listaNativos){
                    if(p.getNome().equalsIgnoreCase(pn)){ // Se entrar no IF não é processo nativo
                        processoNativo = true;
                    }
                }
                if(!processoNativo){
                    res = chat.verificarProcesso(p.getNome());
                    System.out.println(p.getNome());
                    System.out.println(res);
                    if(res.equalsIgnoreCase("sim")){
                        System.out.println("Finalizando o seguinte processo: "+p.getNome());
                        Runtime.getRuntime().exec("taskkill /F /IM " + p.getNome()+".exe");
                    }
                    Thread.sleep(10000);
                }

        }
    }
    }
}
