package com.projetolp3.domain.usecase;

import android.support.annotation.NonNull;

import com.projetolp3.data.adapters.LembreteRepositorio;
import com.projetolp3.domain.adapters.CasoUso;
import com.projetolp3.domain.model.ModeloLembrete;

public class EditarLembrete extends CasoUso<EditarLembrete.RequisicaoValores, EditarLembrete.RespostaValor> {

    private final LembreteRepositorio mLembreteRepositorio;

    public EditarLembrete(@NonNull LembreteRepositorio lembreteRepositorio) {
        mLembreteRepositorio = lembreteRepositorio;
    }

    @Override
    protected void executaCasoUso(EditarLembrete.RequisicaoValores requisicaoValores) {
        ModeloLembrete lembrete = requisicaoValores.getLembrete();
        mLembreteRepositorio.salvaLembrete(lembrete);
        getmCasoUsoCallback().onSucesso(new EditarLembrete.RespostaValor(lembrete));
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
        private final ModeloLembrete mLembrete;

        public RespostaValor(@NonNull ModeloLembrete lembrete) {
            mLembrete = lembrete;
        }

        public ModeloLembrete getLembrete() {
            return mLembrete;
        }
    }
}
