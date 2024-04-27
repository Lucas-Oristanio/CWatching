package com.cw.dao;

import com.cw.conexao.Conexao;
import com.cw.models.Empresa;
import com.cw.models.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UsuarioDAO {
    private final Conexao conexao = new Conexao();
    private final JdbcTemplate con = conexao.getConexaoDoBanco();

    public UsuarioDAO() {

    }

    public Usuario buscarUsuarioPorUsername(String username) {

        Usuario usuario = con.queryForObject("SELECT * FROM usuario WHERE username = '%s'".formatted(username), new BeanPropertyRowMapper<>(Usuario.class));

        return usuario;
    }

    public Boolean autenticarLogin(String username, String senha) {

        List<Usuario> usuario = con.query("SELECT * FROM usuario WHERE username = '%s' AND senha = BINARY '%s'".formatted(username, senha), new BeanPropertyRowMapper<>(Usuario.class));

        return usuario.size() == 1;
    }

    public Empresa buscarEmpresaPorUsername(String username) {
//        Integer id = 0;

        String sql = "SELECT * FROM empresa JOIN funcionario ON fk_empresa = id_empresa JOIN usuario ON id_usuario = id_funcionario WHERE username = '%s'".formatted(username);
//        Empresa empresa = con.queryForObject(sql, new BeanPropertyRowMapper<>(Empresa.class));

//        if (empresa != null) id = empresa.getIdEmpresa();

        return con.queryForObject(sql, new BeanPropertyRowMapper<>(Empresa.class));
    }
}
