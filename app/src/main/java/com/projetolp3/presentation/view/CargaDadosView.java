package com.projetolp3.presentation.view;

import android.content.Context;
/**
 * Created by alef_ on 29/03/2018.
 */

/**
 * Exemplo de interface usada por uma view que usa carga de dados
 */
public interface CargaDadosView {

    /**
     * Mostra uma barra de carregando.
     */
    void mostraCarregando();

    /**
     * Esconde barra de carregando.
     */

    void escondeCarregando();

    /**
     * Mostra uma view de tentar novamente mostrada no caso de erro enquanto carrega dados.
     */

    void mostraTentaNovamente();

    /**
     * Esconde uma view de tentar novamente mostrada no caso de erro enquando carrega dados.
     */

    void escondeTentaNovamente();

    /**
     * Mostra uma mensagem de erro
     *
     * @param mensagem Um string correpondente ao erro
     */

    void mostraErro(String mensagem);

    /**
     * Pega um {@link android.content.Context}.

     */

    Context contexto();
}
