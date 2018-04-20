package com.projetolp3.domain.adapters;

import android.os.Handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CasoUsoThreadPoolAgendador implements CasoUsoAgendador {

    private final Handler mHandler = new Handler();

    public static final int POOL_SIZE = 2;

    public static final int MAX_POOL_SIZE = 4;

    public static final int TIMEOUT = 30;

    ThreadPoolExecutor mThreadPoolExecutor;

    public CasoUsoThreadPoolAgendador() {
        mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(POOL_SIZE));
    }

    @Override
    public void executa(Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

    @Override
    public <V extends CasoUso.respostaValor> void notificaResposta(final V resposta,
                                                                   final CasoUso.CasoUsoCallback<V> casoUsoCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                casoUsoCallback.onSucesso(resposta);
            }
        });
    }

    @Override
    public <V extends CasoUso.respostaValor> void onErro(
            final CasoUso.CasoUsoCallback<V> casoUsoCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                casoUsoCallback.onErro();
            }
        });
    }
}
