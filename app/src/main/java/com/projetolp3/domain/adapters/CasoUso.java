package com.projetolp3.domain.adapters;

/**
 * Created by alef_ on 29/03/2018.
 */

/**
 * Casos de uso são os pontos de entrada para a camada de domínio.
 *
 * @param <Q> o tipo da requisicao.
 * @param <P> o tipo da resposta.
 */
public abstract class CasoUso<Q extends CasoUso.requisicaoValores, P extends CasoUso.respostaValor> {
    private Q mRequisicaoValores;

    private CasoUsoCallback<P> mCasoUsoCallback;

    public Q getmRequisicaoValores() {
        return mRequisicaoValores;
    }

    public void setmRequisicaoValores(Q mRequisicaoValores) {
        this.mRequisicaoValores = mRequisicaoValores;
    }

    public CasoUsoCallback<P> getmCasoUsoCallback() {
        return mCasoUsoCallback;
    }

    public void setmCasoUsoCallback(CasoUsoCallback<P> mCasoUsoCallback) {
        this.mCasoUsoCallback = mCasoUsoCallback;
    }

    void run() {
        executaCasoUso(mRequisicaoValores);
    }

    protected abstract void executaCasoUso(Q requisicaoValores);

    /**
     * Dados passados para uma requisicao
     */

    public interface requisicaoValores{

    }

    /**
     * Dados recebidos de uma requisicao
     */

    public interface respostaValor{

    }

    public interface CasoUsoCallback<R>{
        void onSucesso(R resposta);
        void onErro();
    }
}