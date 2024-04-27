package com.cw.dao;

import com.cw.conexao.Conexao;
import com.cw.models.Empresa;
import com.cw.models.Maquina;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MaquinaDAO {
    private Conexao conexao = new Conexao();
    private JdbcTemplate con = conexao.getConexaoDoBanco();

    public MaquinaDAO() {
    }

    public void inserirMaquina(Maquina m) {
        String sql = "INSERT INTO maquina (so, cpu, ram, hostname, fk_empresa) VALUES (?, ?, ?, ?, ?)";
        con.update(sql, m.getSo(), m.getCpu(), m.getRam(), m.getHostname(), m.getFkEmpresa());
    }

    public Boolean verificarMaquinaExistePorHostnameEEmpresa(String hostname, Empresa e) {
        String sql = "SELECT hostname FROM maquina WHERE hostname = '%s' AND fk_empresa = %d".formatted(hostname, e.getIdEmpresa());
        return !con.query(sql, new BeanPropertyRowMapper<>(Maquina.class)).isEmpty();
    }

    public Maquina buscarMaquinaPorHostnameEEmpresa(String hostname, Empresa e) {
        String sql = "SELECT * FROM maquina WHERE hostname = '%s' AND fk_empresa = %d".formatted(hostname, e.getIdEmpresa());
        return con.queryForObject(sql, new BeanPropertyRowMapper<>(Maquina.class));
    }
}
