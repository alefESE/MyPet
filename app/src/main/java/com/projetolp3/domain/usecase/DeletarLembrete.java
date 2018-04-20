package com.projetolp3.domain.usecase;

import android.support.annotation.NonNull;

import com.projetolp3.data.adapters.LembreteRepositorio;
import com.projetolp3.domain.adapters.CasoUso;

public class DeletarLembrete extends CasoUso<DeletarLembrete.RequisicaoValores, DeletarLembrete.RespostaValor> {

    private final LembreteRepositorio mLembreteRepositorio;

    public DeletarLembrete(@NonNull LembreteRepositorio lembreteRepositorio) {
        mLembreteRepositorio = lembreteRepositorio;
    }

    @Override
    protected void executaCasoUso(DeletarLembrete.RequisicaoValores requisicaoValores) {
        int id = requisicaoValores.getId();
        mLembreteRepositorio.deletaLembrete(id);
        getmCasoUsoCallback().onSucesso(new DeletarLembrete.RespostaValor());
    }


    public static final class RequisicaoValores implements CasoUso.requisicaoValores {
        private final int id;

        public RequisicaoValores(int id){
            this.id = id;
        }

        public int getId(){
            return id;
        }
    }

    public static final class RespostaValor implements CasoUso.respostaValor {

    }
}
