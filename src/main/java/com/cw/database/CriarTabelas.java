package com.cw.database;

import com.cw.conexao.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

public class CriarTabelas {
    public static void criarTabelas() {
        String sql = """
            DROP DATABASE IF EXISTS cwdb;
            CREATE DATABASE cwdb;
            USE cwdb;
            
            CREATE TABLE endereco (
                id_endereco INT PRIMARY KEY AUTO_INCREMENT,
                logradouro VARCHAR(45),
                cep CHAR(8),
                numero VARCHAR(5),
                complemento VARCHAR(45),
                cidade VARCHAR(45) NOT NULL,
                uf CHAR(2)
            );
                        
            CREATE TABLE empresa (
                id_empresa INT PRIMARY KEY AUTO_INCREMENT,
                nome_fantasia VARCHAR(45) NOT NULL,
                razao_social VARCHAR(45) NOT NULL,
                cnpj CHAR(14) NOT NULL,
                fk_matriz INT,
                CONSTRAINT fk_matriz_empresa FOREIGN KEY (fk_matriz) REFERENCES empresa(id_empresa),
                fk_endereco INT,
                CONSTRAINT fk_endereco_empresa FOREIGN KEY (fk_endereco) REFERENCES endereco(id_endereco)
            ) AUTO_INCREMENT = 1000;
            
            CREATE TABLE processo_perm (
                id_processo INT PRIMARY KEY AUTO_INCREMENT,
                nome VARCHAR(45),
                perm CHAR(1) NOT NULL,
                CONSTRAINT fk_empresa_processo_perm FOREIGN KEY (id_processo) REFERENCES empresa(id_empresa)
            );
                        
            CREATE TABLE parametro_alerta (
                id_parametro INT PRIMARY KEY,
                max_cpu DECIMAL(4, 1),
                max_ram DECIMAL(4, 1),
                max_volume DECIMAL(4, 1),
                sensibilidade_mouse INT,
                timer_mouse_ms INT,
                intervalo_registro_ms INT,
                intervalo_volume_ms INT,
                CONSTRAINT fk_empresa_parametro FOREIGN KEY (id_parametro) REFERENCES empresa(id_empresa)
            );
                        
            CREATE TABLE funcionario (
                id_funcionario INT PRIMARY KEY AUTO_INCREMENT,
                primeiro_nome VARCHAR(45),
                sobrenome VARCHAR(45) NOT NULL,
                celular CHAR(11),
                telefone CHAR(11),
                email VARCHAR(60) NOT NULL,
                dt_nasc DATE,
                cpf CHAR(14) NOT NULL,
                cargo VARCHAR(45), -- Picklist
                fk_supervisor INT,
                fk_empresa INT,
                CONSTRAINT fk_supervisor_funcionario FOREIGN KEY (fk_supervisor) REFERENCES funcionario(id_funcionario),
                CONSTRAINT fk_empresa_funcionario FOREIGN KEY (fk_empresa) REFERENCES empresa(id_empresa)
            );
                        
            CREATE TABLE apontamento (
                id_apontamento INT PRIMARY KEY AUTO_INCREMENT,
                dt_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
                detalhe VARCHAR(2000),
                fk_funcionario INT NOT NULL,
                CONSTRAINT fk_apontamento_funcionario FOREIGN KEY (fk_funcionario) REFERENCES funcionario(id_funcionario)
            );
                        
            CREATE TABLE tarefa (
                id_tarefa INT PRIMARY KEY AUTO_INCREMENT,
                descricao VARCHAR(255),
                dt_fim DATE,
                dt_inicio DATE,
                concluida TINYINT DEFAULT 0,
                dt_concluida DATE DEFAULT (CURRENT_DATE),
                fk_funcionario INT NOT NULL,
                fk_supervisor INT NOT NULL,
                CONSTRAINT fk_funcionario_tarefa FOREIGN KEY (fk_funcionario) REFERENCES funcionario(id_funcionario),
                CONSTRAINT fk_supervisor_tarefa FOREIGN KEY (fk_supervisor) REFERENCES funcionario(id_funcionario)
            );
                        
            CREATE TABLE usuario (
                id_usuario INT PRIMARY KEY,
                username VARCHAR(80),
                senha VARCHAR(80) CHECK (LENGTH(senha) >= 8),
                dt_criado DATETIME DEFAULT CURRENT_TIMESTAMP,
                CONSTRAINT fk_funcionario_usuario FOREIGN KEY (id_usuario) REFERENCES funcionario(id_funcionario)
            );
                        
            CREATE TABLE tempo_ociosidade (
                id_tempo_ociosidade INT PRIMARY KEY AUTO_INCREMENT,
                dt_hora_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
                tempo_registro_seg INT,
                fk_usuario INT,
                CONSTRAINT fk_usuario_tempo_ociosidade FOREIGN KEY (fk_usuario) REFERENCES usuario(id_usuario)
            );
                        
            CREATE TABLE artigo (
                id_artigo INT PRIMARY KEY AUTO_INCREMENT,
                titulo VARCHAR(25),
                descricao VARCHAR(2000),
                categoria VARCHAR(45),
                palavra_chave VARCHAR(45),
                fk_funcionario INT,
                CONSTRAINT fk_funcionario_artigo FOREIGN KEY (fk_funcionario) REFERENCES funcionario(id_funcionario)
            );
                        
            CREATE TABLE maquina (
                id_maquina INT PRIMARY KEY AUTO_INCREMENT,
                hostname VARCHAR(80),
                so VARCHAR(80),
                cpu VARCHAR(80),
                ram BIGINT,
                fk_empresa INT,
                CONSTRAINT fk_empresa_maquina FOREIGN KEY (fk_empresa) REFERENCES empresa(id_empresa)
            );
                        
            CREATE TABLE sessao (
                id_sessao INT PRIMARY KEY AUTO_INCREMENT,
                fk_maquina INT,
                fk_usuario INT,
                dt_hora_sessao DATETIME DEFAULT CURRENT_TIMESTAMP,
                CONSTRAINT fk_maquina_sessao FOREIGN KEY (fk_maquina) REFERENCES maquina(id_maquina),
                CONSTRAINT fk_usuario_sessao FOREIGN KEY (fk_usuario) REFERENCES usuario(id_usuario)
            );
                        
            CREATE TABLE ocorrencia (
                id_ocorrencia INT PRIMARY KEY AUTO_INCREMENT,
                titulo VARCHAR(45),
                descricao VARCHAR(255),
                dt_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
                prioridade VARCHAR(45),
                fk_sessao INT,
                CONSTRAINT fk_sessao_ocorrencia FOREIGN KEY (fk_sessao) REFERENCES sessao(id_sessao)
            );
                        
            CREATE TABLE registro (
                id_registro INT PRIMARY KEY AUTO_INCREMENT,
                dt_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
                uso_cpu DECIMAL(4, 1),
                uso_ram BIGINT,
                disponivel_ram BIGINT,
                fk_sessao INT,
                CONSTRAINT fk_sessao_registro FOREIGN KEY (fk_sessao) REFERENCES sessao(id_sessao)
            );
                        
            CREATE TABLE volume (
                uuid CHAR(36) PRIMARY KEY,
                fk_maquina INT,
                nome VARCHAR(45),
                ponto_montagem VARCHAR(45),
                CONSTRAINT fk_maquina_volume FOREIGN KEY (fk_maquina) REFERENCES maquina(id_maquina)
            );
                        
            CREATE TABLE registro_volume (
                id_registro_volume INT PRIMARY KEY AUTO_INCREMENT,
                volume_disponivel BIGINT,
                volume_total BIGINT,
                dt_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
                fk_volume CHAR(36) NOT NULL,
                CONSTRAINT fk_registro_volume FOREIGN KEY (fk_volume) REFERENCES volume(uuid)
            );
                 
             CREATE TABLE processo (
                 id_processo INT PRIMARY KEY AUTO_INCREMENT,
                 nome VARCHAR(45),
                 caminho VARCHAR(255),
                 uso_ram BIGINT,
                 fk_registro INT,
                 CONSTRAINT fk_registro_processo FOREIGN KEY (fk_registro) REFERENCES registro(id_registro)
                 )
            """;

        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        String[] comandos = sql.split(";");

        for (int i = 0; i < comandos.length; i++) {
            con.execute(comandos[i]);
        }
    }
}
