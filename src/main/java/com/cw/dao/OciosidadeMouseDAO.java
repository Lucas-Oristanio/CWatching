package com.cw.dao;

import com.cw.conexao.Conexao;
import com.cw.models.RegistroOciosidadeMouse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OciosidadeMouseDAO {
    private Conexao conexao;
    private JdbcTemplate con;

    public OciosidadeMouseDAO() {
        this.conexao = new Conexao();
        this.con = conexao.getConexaoDoBanco();
    }

    public void inserirOciosidadeMouse(RegistroOciosidadeMouse registro) {
        String sql = "INSERT INTO tempo_ociosidade (tempo_registro_seg, fk_usuario) VALUES (?, ?)";

        con.update(sql, registro.getTempoRegistroSeg(), registro.getFkUsuario());
    }

    public List<RegistroOciosidadeMouse> buscarRegistroOciosidadeUsuario (Integer fkUsuario) {
        String sql = "SELECT * FROM tempo_ociosidade WHERE fk_usuario = " + fkUsuario;
        List<RegistroOciosidadeMouse> registros = con.query(sql, new BeanPropertyRowMapper<>(RegistroOciosidadeMouse.class));

        return registros;
    }

}
