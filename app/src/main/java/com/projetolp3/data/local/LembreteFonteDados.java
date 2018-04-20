package com.projetolp3.data.local;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.projetolp3.data.util.CRUDs.LembreteCRUD;
import com.projetolp3.data.local.DAOs.LembreteDAO;
import com.projetolp3.domain.model.ModeloLembrete;
import com.projetolp3.util.AppExecutors;

import java.util.List;

public class LembreteFonteDados implements LembreteCRUD {

    private static volatile LembreteFonteDados INSTANCIA;

    private LembreteDAO mLembreteDAO;

    private AppExecutors mAppExecutors;

    private LembreteFonteDados(@NonNull AppExecutors appExecutors,
                               @NonNull LembreteDAO lembreteDAO) {
        mAppExecutors = appExecutors;
        mLembreteDAO = lembreteDAO;
    }

    public static LembreteFonteDados getInstacia(@NonNull AppExecutors appExecutors,
                                                 @NonNull LembreteDAO lembreteDAO) {
        if(INSTANCIA == null) {
            synchronized (LembreteFonteDados.class) {
                if(INSTANCIA == null){
                    INSTANCIA = new LembreteFonteDados(appExecutors, lembreteDAO);
                }
            }
        }
        return INSTANCIA;
    }

    @Override
    public void refrescaLembrete() {

    }

    @Override
    public void getLembretes(@NonNull final CarregaLembretesCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ModeloLembrete> lembretes = mLembreteDAO.getLembretes();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(lembretes.isEmpty()){
                            callback.onDadosNaoDisponiveis();
                        }else{
                            callback.onLembretesCarregados(lembretes);
                        }
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getLembrete(final int lembreteId, @NonNull final GetLembreteCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final ModeloLembrete lembrete = mLembreteDAO.getLembretePorId(lembreteId);

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(lembrete != null){
                            callback.onLembreteCarregado(lembrete);
                        }else{
                            callback.onDadosNaoDisponiveis();
                        }
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void salvaLembrete(@NonNull final ModeloLembrete lembreteModelo) {
        //checkNotNull(lembreteModelo);
        Runnable salvaRunnable = new Runnable() {
            @Override
            public void run() {
                mLembreteDAO.insereLembrete(lembreteModelo);
            }
        };

        mAppExecutors.diskIO().execute(salvaRunnable);
    }

    @Override
    public void deletaLembretes() {
        Runnable deletaRunnable = new Runnable() {
            @Override
            public void run() {
                mLembreteDAO.deletaLembretes();
            }
        };

        mAppExecutors.diskIO().execute(deletaRunnable);
    }

    @Override
    public void deletaLembrete(final int lembreteId) {
        Runnable deletaRunnable = new Runnable() {
            @Override
            public void run() {
                mLembreteDAO.deletaLembretePorId(lembreteId);
            }
        };

        mAppExecutors.diskIO().execute(deletaRunnable);
    }

    @VisibleForTesting
    static void limpaInstacia(){
        INSTANCIA = null;
    }
}
