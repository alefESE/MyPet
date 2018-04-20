package com.projetolp3.data.local.CRUDS;

import android.support.annotation.NonNull;

import com.projetolp3.domain.model.ModeloLembrete;

import java.util.List;

public interface LembreteCRUD {
    interface CarregaLembretesCallback {
        void onLembretesCarregados(List<ModeloLembrete> lembreteModelo);
        void onDadosNaoDisponiveis();
    }
    interface GetLembreteCallback {
        void onLembreteCarregado(ModeloLembrete lembreteModelo);
        void onDadosNaoDisponiveis();
    }
    void getLembretes(@NonNull CarregaLembretesCallback callback);
    void getLembrete(int LembreteId, @NonNull GetLembreteCallback callback);
    void salvaLembrete(@NonNull ModeloLembrete usuarioModelo);
    void deletaLembretes();
    void deletaLembrete(int lembreteId);
}
