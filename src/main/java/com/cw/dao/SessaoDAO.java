package com.cw.dao;

import com.cw.conexao.Conexao;
import com.cw.models.Empresa;
import com.cw.models.Maquina;
import com.cw.models.Sessao;
import com.cw.models.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SessaoDAO {
    private final Conexao conexao = new Conexao();
    private final JdbcTemplate con = conexao.getConexaoDoBanco();

    public SessaoDAO() {
    }

    public void registrarSessao(Integer fkMaquina, Integer fkUsuario) {
        String sql = "INSERT INTO sessao (fk_maquina, fk_usuario) VALUES (?, ?)";
        con.update(sql, fkMaquina, fkUsuario);
    }

    public Sessao buscarUltimaSessaoPorMaquina(Integer fkMaquina) {
        String sql = "SELECT * FROM sessao WHERE fk_maquina = %d ORDER BY dt_hora_sessao DESC LIMIT 1".formatted(fkMaquina);

        return con.queryForObject(sql, new BeanPropertyRowMapper<>(Sessao.class));
    }
}
