package com.projetolp3.presenter.view;

/**
 * Created by alef_ on 29/03/2018.
 */

import com.projetolp3.domain.model.ModeloUsuario;

/**
 * Interface representando uma View no padrão (MVP)
 * Exemplo de view apresentando um perfil de usuario.
 */
public interface DetalhesUsuarioView {
    /**
     *
     * Renderiza um usuario na interface grafica.
     *
     * @param usuario The {@link ModeloUsuario} that will be shown.
     */

    void renderizaUsuario(ModeloUsuario usuario);

}
