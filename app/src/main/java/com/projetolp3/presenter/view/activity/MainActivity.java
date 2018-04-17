package com.projetolp3.presenter.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.projetolp3.mypet.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void botaoAdicionar(View view) {
        startActivity(new Intent(MainActivity.this, TarefasActivity.class));
    }

    public void editarPerfil(View view) {
        startActivity( new Intent( MainActivity.this, PerfilActivity.class ) );
    }
}
