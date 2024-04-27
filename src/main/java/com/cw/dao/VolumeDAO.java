package com.cw.dao;

import com.cw.conexao.Conexao;
import com.cw.models.Empresa;
import com.cw.models.Maquina;
import com.cw.models.Volume;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class VolumeDAO {
    private final Conexao conexao = new Conexao();
    private final JdbcTemplate con = conexao.getConexaoDoBanco();

    public VolumeDAO() {
    }

    public void inserirVolume(Volume v) {
        String sql = "INSERT INTO volume (uuid, nome, ponto_montagem, fk_maquina) VALUES (?, ?, ?, ?)";
        con.update(sql, v.getUUID(), v.getNome(), v.getPontoMontagem(), v.getFkMaquina());
    }

    public List<Volume> buscarVolumesPorMaquina(Maquina m) {
        String sql = "SELECT * FROM volume WHERE fk_maquina = %d".formatted(m.getIdMaquina());

        return con.query(sql, new BeanPropertyRowMapper<>(Volume.class));
    }
}
