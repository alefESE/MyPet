package com.projetolp3.domain.usecase;

import android.support.annotation.NonNull;

import com.projetolp3.data.adapters.LembreteRepositorio;
import com.projetolp3.data.util.CRUDs.LembreteCRUD;
import com.projetolp3.domain.adapters.CasoUso;
import com.projetolp3.domain.model.ModeloLembrete;

import java.util.ArrayList;
import java.util.List;

public class CarregaLembretes extends CasoUso<CarregaLembretes.RequisicaoValores, CarregaLembretes.RespostaValor> {

    private final LembreteRepositorio mLembreteRepositorio;

    public CarregaLembretes(@NonNull LembreteRepositorio lembreteRepositorio) {
        mLembreteRepositorio = lembreteRepositorio;
    }

    @Override
    protected void executaCasoUso(CarregaLembretes.RequisicaoValores requisicaoValores) {

        mLembreteRepositorio.getLembretes(new LembreteCRUD.CarregaLembretesCallback() {
            @Override
            public void onLembretesCarregados(List<ModeloLembrete> lembreteModelo) {
                List<ModeloLembrete> lembretes = new ArrayList<>(lembreteModelo);
                RespostaValor respostaValor = new RespostaValor(lembretes);
                getmCasoUsoCallback().onSucesso(respostaValor);
            }

            @Override
            public void onDadosNaoDisponiveis() {
                getmCasoUsoCallback().onErro();
            }
        });
    }


    public static final class RequisicaoValores implements CasoUso.requisicaoValores {

    }

    public static final class RespostaValor implements CasoUso.respostaValor {
        private final List<ModeloLembrete> mLembretes;

        public RespostaValor(@NonNull List<ModeloLembrete> lembretes) {
            mLembretes = lembretes;
        }

        public List<ModeloLembrete> getLembretes() {
            return mLembretes;
        }
    }
}
