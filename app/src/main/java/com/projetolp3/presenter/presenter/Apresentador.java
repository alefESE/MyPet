package com.projetolp3.presenter.presenter;

/**
 * Created by alef_ on 29/03/2018.
 */

/**
 * Interface representando um Apresentador no padrao (MVP)
 */
public interface Apresentador {

    /**
     * Metodo que controla o ciclo de vida de uma view. Ele deve ser chamado no metodo onResume()
     * da (Acitivity ou Fragmento) da view.
     */
    void resume();

    /**
     * Metodo que controla o ciclo de vida de uma view. Ele deve ser chamado no metodo onPause()
     * da (Acitivity ou Fragmento) da view.
     */
    void pause();

    /**
     * Metodo que controla o ciclo de vida de uma view. Ele deve ser chamado no metodo onDestroy()
     * da (Acitivity ou Fragmento) da view.
     */
    void destroy();
}
