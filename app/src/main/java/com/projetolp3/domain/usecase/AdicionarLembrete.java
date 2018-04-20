package com.projetolp3.domain.usecase;

import android.support.annotation.NonNull;

import com.projetolp3.data.adapters.LembreteRepositorio;
import com.projetolp3.domain.adapters.CasoUso;
import com.projetolp3.domain.model.ModeloLembrete;

public class AdicionarLembrete extends CasoUso<AdicionarLembrete.RequisicaoValores, AdicionarLembrete.RespostaValor> {

    private final LembreteRepositorio mLembreteRepositorio;

    public AdicionarLembrete(@NonNull LembreteRepositorio lembreteRepositorio) {
        mLembreteRepositorio = lembreteRepositorio;
    }

    @Override
    protected void executaCasoUso(RequisicaoValores requisicaoValores) {
        ModeloLembrete lembrete = requisicaoValores.getLembrete();
        mLembreteRepositorio.salvaLembrete(lembrete);
        getmCasoUsoCallback().onSucesso(new RespostaValor());
    }


    public static final class RequisicaoValores implements CasoUso.requisicaoValores {
        private final ModeloLembrete mLembrete;

        public RequisicaoValores(@NonNull ModeloLembrete lembrete){
            mLembrete = lembrete;
        }

        public ModeloLembrete getLembrete() {
            return mLembrete;
        }
    }

    public static final class RespostaValor implements CasoUso.respostaValor {

    }
}
