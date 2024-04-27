package com.cw.dao;

import com.cw.conexao.Conexao;
import com.cw.models.Empresa;
import com.cw.models.ParametroAlerta;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ParametroAlertaDAO {
    private final Conexao conexao = new Conexao();
    private final JdbcTemplate con = conexao.getConexaoDoBanco();

    public void inserirParametroAlerta(Empresa e, ParametroAlerta t) {
        String sql = "INSERT INTO parametro_alerta (id_parametro, max_cpu, max_ram, max_volume, sensibilidade_mouse, registro_mouse_ms, registro_maquina_ms) VALUES (?, ?, ?, ?, ?, ?, ?)";
        con.update(sql, e.getIdEmpresa(), t.getMaxCpu(), t.getMaxRam(), t.getMaxVolume(), t.getSensibilidadeMouse(), t.getTimerMouseMs(), t.getIntervaloRegistroMs());
    }

    public ParametroAlerta buscarParametroAlertaPorEmpresa(Empresa e) {
        String sql = "SELECT * FROM parametro_alerta WHERE id_parametro = %d".formatted(e.getIdEmpresa());
        return con.queryForObject(sql, new BeanPropertyRowMapper<>(ParametroAlerta.class));
    }
}
