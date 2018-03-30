package com.projetolp3.domain.model;

/**
 * Created by alef_ on 29/03/2018.
 */

/**
 * Classe que representa um usuario na camada de Apresentacao
 */
public class ModeloUsuario {
    private final int usuarioId;

    public ModeloUsuario(int usuarioId){
        this.usuarioId = usuarioId;
    }

    private String capaUrl;
    private String nomeCompleto;
    private String email;
    private String descricao;
    private int seguidores;

    public int getUsuarioId() {
        return usuarioId;
    }

    public String getCapaUrl() {
        return capaUrl;
    }

    public void setCapaUrl(String capaUrl) {
        this.capaUrl = capaUrl;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }
}
