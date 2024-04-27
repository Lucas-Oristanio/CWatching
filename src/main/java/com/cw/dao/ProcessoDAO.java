package com.cw.dao;

import com.cw.conexao.Conexao;
import com.cw.models.Processo;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProcessoDAO {
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();

    public void inserirProcesso(Processo p) {
        String sql = "INSERT INTO processo (nome, caminho, uso_ram, fk_registro) VALUES (?, ?, ?, ?)";
        con.update(sql, p.getNome(), p.getCaminho(), p.getUsoRam(), p.getFkRegistro());
    }
}
