package com.projetolp3.data.adapters;

import android.support.annotation.NonNull;

import com.projetolp3.data.util.CRUDs.LembreteCRUD;
import com.projetolp3.data.local.LembreteFonteDados;
import com.projetolp3.domain.model.ModeloLembrete;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LembreteRepositorio implements LembreteCRUD {

    private static LembreteRepositorio INSTANCIA = null;

    private final LembreteFonteDados mLembreteFonteDados;

    private final LembreteFonteDados mLembreteFonteDadosRemoto;

    Map<Integer, ModeloLembrete> mCachedLembrete;

    boolean mCachedSujo = false;

    private LembreteRepositorio(@NonNull LembreteFonteDados lembreteFonteDadosRemoto,
                                @NonNull LembreteFonteDados lembreteFonteDados) {
        mLembreteFonteDadosRemoto = lembreteFonteDadosRemoto;
        mLembreteFonteDados = lembreteFonteDados;
    }

    public static LembreteRepositorio getInstancia(LembreteFonteDados lembreteFonteDadosRemoto,
                                                   LembreteFonteDados lembreteFonteDados) {
        if(INSTANCIA == null){
            INSTANCIA = new LembreteRepositorio(lembreteFonteDadosRemoto, lembreteFonteDados);
        }
        return INSTANCIA;
    }

    public static void destroiInstancia() {
        INSTANCIA = null;
    }

    @Override
    public void getLembretes(@NonNull final CarregaLembretesCallback callback) {
        if(mCachedLembrete != null && !mCachedSujo){
            callback.onLembretesCarregados(new ArrayList<>(mCachedLembrete.values()));
            return;
        }

        if(mCachedSujo) {
            getLembretesFonteDadosRemoto(callback);
        } else {
            mLembreteFonteDados.getLembretes(new CarregaLembretesCallback() {
                @Override
                public void onLembretesCarregados(List<ModeloLembrete> lembreteModelo) {
                    refrescaCache(lembreteModelo);
                    callback.onLembretesCarregados(new ArrayList<>(mCachedLembrete.values()));
                }

                @Override
                public void onDadosNaoDisponiveis() {
                    getLembretesFonteDadosRemoto(callback);
                }
            });
        }
    }


    @Override
    public void getLembrete(final int lembreteId, @NonNull final GetLembreteCallback callback) {
        ModeloLembrete cachedLembrete = getLembreteComId(lembreteId);

        if(cachedLembrete != null){
            callback.onLembreteCarregado(cachedLembrete);
            return;
        }

        mLembreteFonteDados.getLembrete(lembreteId, new GetLembreteCallback() {
            @Override
            public void onLembreteCarregado(ModeloLembrete lembreteModelo) {
                if(mCachedLembrete == null){
                    mCachedLembrete = new LinkedHashMap<>();
                }
                mCachedLembrete.put(lembreteModelo.getId(), lembreteModelo);
                callback.onLembreteCarregado(lembreteModelo);
            }

            @Override
            public void onDadosNaoDisponiveis() {
                mLembreteFonteDadosRemoto.getLembrete(lembreteId, new GetLembreteCallback() {
                    @Override
                    public void onLembreteCarregado(ModeloLembrete lembreteModelo) {
                        if(mCachedLembrete == null){
                            mCachedLembrete = new LinkedHashMap<>();
                        }
                        mCachedLembrete.put(lembreteModelo.getId(), lembreteModelo);
                        callback.onLembreteCarregado(lembreteModelo);
                    }

                    @Override
                    public void onDadosNaoDisponiveis() {
                        callback.onDadosNaoDisponiveis();
                    }
                });
            }
        });
    }

    @Override
    public void salvaLembrete(@NonNull ModeloLembrete lembreteModelo) {
        mLembreteFonteDadosRemoto.salvaLembrete(lembreteModelo);
        mLembreteFonteDados.salvaLembrete(lembreteModelo);

        if(mCachedLembrete == null){
            mCachedLembrete = new LinkedHashMap<>();
        }
        mCachedLembrete.put(lembreteModelo.getId(), lembreteModelo);
    }

    @Override
    public void deletaLembretes() {
        mLembreteFonteDadosRemoto.deletaLembretes();
        mLembreteFonteDados.deletaLembretes();

        if(mCachedLembrete == null){
            mCachedLembrete = new LinkedHashMap<>();
        }
        mCachedLembrete.clear();
    }

    @Override
    public void deletaLembrete(int lembreteId) {
        mLembreteFonteDados.deletaLembrete(lembreteId);
        mLembreteFonteDadosRemoto.deletaLembrete(lembreteId);

        mCachedLembrete.remove(lembreteId);
    }

    @Override
    public void refrescaLembrete() {
        mCachedSujo = true;
    }

    private void refrescaCache(List<ModeloLembrete> lembretes) {
        if(mCachedLembrete == null) {
            mCachedLembrete = new LinkedHashMap<>();
        }

        mCachedLembrete.clear();
        for(ModeloLembrete lembrete : lembretes) {
            mCachedLembrete.put(lembrete.getId(), lembrete);
        }
        mCachedSujo = false;
    }

    private void refrescaFonteDados(List<ModeloLembrete> lembretes) {
        mLembreteFonteDados.deletaLembretes();
        for(ModeloLembrete lembrete : lembretes) {
            mLembreteFonteDados.salvaLembrete(lembrete);
        }
    }

    public void getLembretesFonteDadosRemoto(@NonNull final CarregaLembretesCallback callback) {
        mLembreteFonteDadosRemoto.getLembretes(new CarregaLembretesCallback() {
            @Override
            public void onLembretesCarregados(List<ModeloLembrete> lembreteModelo) {
                refrescaCache(lembreteModelo);
                refrescaFonteDados(lembreteModelo);
                callback.onLembretesCarregados(new ArrayList<>(mCachedLembrete.values()));
            }

            @Override
            public void onDadosNaoDisponiveis() {
                callback.onDadosNaoDisponiveis();
            }
        });
    }

    private ModeloLembrete getLembreteComId(int id){
        if(mCachedLembrete == null || mCachedLembrete.isEmpty()) {
            return null;
        }else{
            return mCachedLembrete.get(id);
        }
    }
}
