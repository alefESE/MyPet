package com.projetolp3.presentation.view;

/**
 * Created by alef_ on 29/03/2018.
 */

import com.projetolp3.domain.model.ModeloUsuario;

import java.util.Collection;

/**
 * Interface representando uma View no padrão (MVP).
 * Exemplo de view apresentando uma lista de {@link ModeloUsuario}.
 */
public interface ListaUsuarioView {
    /**
     * Renderiza lista de usuarios na interface grafica.
     *
     * @param usuarioModeloCollection A coleção de {@link ModeloUsuario} que sera mostrada.
     */

    void renderizaUsuarioLista(Collection<ModeloUsuario> usuarioModeloCollection);

    /**
     * View a {@link ModeloUsuario} profile/details.
     *
     * @param usuarioModelo The user that will be shown.
     */

    void viewUsuario(ModeloUsuario usuarioModelo);
}
