package com.cw.database;

import com.cw.conexao.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

public class PopularTabelas {
    public static void popularTabelas() {
        String sql = """
                -- ENDEREÇOS
                INSERT INTO endereco (logradouro, cep, numero, complemento, cidade, uf)
                VALUES ('Rua das Flores', '12345678', '101', 'Bloco A', 'São Paulo', 'SP');
                                
                INSERT INTO endereco (logradouro, cep, numero, complemento, cidade, uf)
                VALUES ('Avenida Copacabana', '87654321', '202', 'Sala 10', 'Rio de Janeiro', 'RJ');
                                
                -- EMPRESAS
                INSERT INTO empresa (nome_fantasia, razao_social, cnpj, fk_endereco)
                VALUES ('Tech Solutions', 'Tech Solutions Ltda.', '12345678000100', 1);
                                
                INSERT INTO empresa (nome_fantasia, razao_social, cnpj, fk_endereco)
                VALUES ('Global Tech', 'Global Tech S.A.', '98765432000199', 2);
                
                INSERT INTO empresa (nome_fantasia, razao_social, cnpj, fk_matriz, fk_endereco)
                VALUES ('Filial Centro', 'Centro Filial Ltda.', '11223344000188', 1000, 1);
                
                -- PARÂMETROS DE CONFIGURAÇÃO
                INSERT INTO parametro_alerta (id_parametro, max_cpu, max_ram, max_volume, sensibilidade_mouse, timer_mouse_ms, intervalo_registro_ms, intervalo_volume_ms)
                VALUES (1001, 85.0, 80.0, 95.0, 25, 15000, 3000, 40000);
                
                INSERT INTO parametro_alerta (id_parametro, max_cpu, max_ram, max_volume, sensibilidade_mouse, timer_mouse_ms, intervalo_registro_ms, intervalo_volume_ms)
                VALUES (1000, 80.0, 70.0, 90.0, 20, 10000, 2000, 30000);
                
                INSERT INTO parametro_alerta (id_parametro, max_cpu, max_ram, max_volume, sensibilidade_mouse, timer_mouse_ms, intervalo_registro_ms, intervalo_volume_ms)
                VALUES (1002, 75.0, 65.0, 85.0, 25, 25000, 2000, 35000);
                
                -- FUNCIONÁRIOS
                -- Empresa 1
                INSERT INTO funcionario (primeiro_nome, sobrenome, celular, telefone, email, dt_nasc, cpf, cargo, fk_empresa)
                VALUES ('Alice', 'Silva', '11987654321', '1123456789', 'alice@techsolutions.com', '1980-05-15', '123.456.789-10', 'Diretor', 1000);
                                
                INSERT INTO funcionario (primeiro_nome, sobrenome, celular, telefone, email, dt_nasc, cpf, cargo, fk_empresa, fk_supervisor)
                VALUES ('Carlos', 'Santos', '11976543210', '1122334455', 'carlos@techsolutions.com', '1985-10-20', '987.654.321-01', 'Supervisor', 1000, 1);
                                
                INSERT INTO funcionario (primeiro_nome, sobrenome, celular, telefone, email, dt_nasc, cpf, cargo, fk_empresa, fk_supervisor)
                VALUES ('Lucas', 'Oliveira', '11965432109', '1199887766', 'lucas@techsolutions.com', '1990-07-12', '456.789.123-02', 'Operador', 1000, 2);
                             
                -- Empresa 2
                INSERT INTO funcionario (primeiro_nome, sobrenome, celular, telefone, email, dt_nasc, cpf, cargo, fk_empresa)
                VALUES ('Ana', 'Rodrigues', '21876543210', '2133445566', 'ana@globaltech.com', '1975-03-25', '789.456.123-45', 'Diretor', 1001);
                               
                INSERT INTO funcionario (primeiro_nome, sobrenome, celular, telefone, email, dt_nasc, cpf, cargo, fk_empresa, fk_supervisor)
                VALUES ('Pedro', 'Ferreira', '21887654321', '2133556677', 'pedro@globaltech.com', '1992-12-08', '654.321.987-78', 'Suporte', 1001, 4);
                               
                -- Empresa 3
                INSERT INTO funcionario (primeiro_nome, sobrenome, celular, telefone, email, dt_nasc, cpf, cargo, fk_empresa)
                VALUES ('Mariana', 'Souza', '11998765432', '1122667788', 'mariana@filialcentro.com', '1993-08-20', '987.654.321-98', 'Suporte', 1002);
                                
                INSERT INTO funcionario (primeiro_nome, sobrenome, celular, telefone, email, dt_nasc, cpf, cargo, fk_empresa, fk_supervisor)
                VALUES ('Rafael', 'Lima', '11987654321', '1122334455', 'rafael@filialcentro.com', '1995-04-18', '123.456.789-45', 'Operador', 1002, 6);
                
                -- USUÁRIOS
                -- Empresa 1
                INSERT INTO usuario (id_usuario, username, senha)
                VALUES (1, 'alice@techsolutions.com', 'alice123');
                                
                INSERT INTO usuario (id_usuario, username, senha)
                VALUES (2, 'carlos@techsolutions.com', 'carlos456');
                                
                INSERT INTO usuario (id_usuario, username, senha)
                VALUES (3, 'lucas@techsolutions.com', 'lucas789');
                
                -- Empresa 2
                INSERT INTO usuario (id_usuario, username, senha)
                VALUES (4, 'ana@globaltech.com', 'ana12987');
                                
                INSERT INTO usuario (id_usuario, username, senha)
                VALUES (5, 'pedro@globaltech.com', 'pedro654');
                
                -- Empresa 3
                INSERT INTO usuario (id_usuario, username, senha)
                VALUES (6, 'mariana@filialcentro.com', 'mariana321');
                                
                INSERT INTO usuario (id_usuario, username, senha)
                VALUES (7, 'rafael@filialcentro.com', 'rafael123')
                """;

        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        String[] comandos = sql.split(";");

        for (int i = 0; i < comandos.length; i++) {
            con.update(comandos[i]);
        }
    }
}
