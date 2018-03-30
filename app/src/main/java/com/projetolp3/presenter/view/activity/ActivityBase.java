package com.projetolp3.presenter.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by alef_ on 29/03/2018.
 */

public abstract class ActivityBase extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    /**
     * Adiciona um {@link Fragment} ao layout desta activity.
     *
     * @param containerViewId O container view para onde adicionar o fragmento.
     * @param fragmento O fragmento a ser adicionado.
     */

    protected void addFragmento(int containerViewId, Fragment fragmento) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragmento);
        fragmentTransaction.commit();
    }
}
