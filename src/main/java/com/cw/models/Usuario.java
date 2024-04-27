package com.cw.models;

public class Usuario {
    private Integer idUsuario;
    private String username;
    private String senha;
    private String dtCriado;

    public Usuario(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }

    public Usuario() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDtCriado() {
        return dtCriado;
    }

    public void setDtCriado(String dtCriado) {
        this.dtCriado = dtCriado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", senha='" + senha + '\'' +
                ", dtCriado='" + dtCriado + '\'' +
                '}';
    }
}
