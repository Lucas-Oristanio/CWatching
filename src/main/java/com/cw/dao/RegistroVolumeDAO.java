package com.cw.dao;

import com.cw.conexao.Conexao;
import com.cw.models.RegistroVolume;
import com.cw.models.Volume;
import org.springframework.jdbc.core.JdbcTemplate;

public class RegistroVolumeDAO {
    private final Conexao conexao = new Conexao();
    private final JdbcTemplate con = conexao.getConexaoDoBanco();

    public RegistroVolumeDAO() {
    }

    public void inserirRegistroVolume(RegistroVolume r) {
        String sql = "INSERT INTO registro_volume (volume_disponivel, volume_total, fk_volume) VALUES (?, ?, ?)";
        con.update(sql, r.getVolumeDisponivel(), r.getVolumeTotal(), r.getFkVolume());
    }
}
