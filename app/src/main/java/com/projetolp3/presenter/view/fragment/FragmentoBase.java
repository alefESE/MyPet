package com.projetolp3.presenter.view.fragment;

import android.app.Fragment;
import android.widget.Toast;

/**
 * Created by alef_ on 29/03/2018.
 */

/**
 * Classe base {@link android.app.Fragment} para todos os fragentos na aplicação
 */
public abstract class FragmentoBase extends Fragment {
    /**
     * Mostra uma {@link android.widget.Toast} mensagem.
     *
     * @param mensagem Uma string representando a mensagem a ser mostrada.
     */
    protected void mostraToastMessagem(String mensagem) {
        Toast.makeText(getActivity(), mensagem, Toast.LENGTH_SHORT).show();
    }
}
