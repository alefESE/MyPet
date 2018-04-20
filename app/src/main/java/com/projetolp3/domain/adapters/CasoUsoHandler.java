package com.projetolp3.domain.adapters;

public class CasoUsoHandler {

    private static CasoUsoHandler INSTANCIA;

    private final CasoUsoAgendador mCasoUsoAgendador;

    public CasoUsoHandler(CasoUsoAgendador casoUsoAgendador) {
        mCasoUsoAgendador = casoUsoAgendador;
    }

    public <T extends CasoUso.requisicaoValores, R extends CasoUso.respostaValor> void executa(
            final CasoUso<T, R> casoUso, T valores, CasoUso.CasoUsoCallback<R> callback) {
        casoUso.setmRequisicaoValores(valores);
        casoUso.setmCasoUsoCallback(new UiCallbackWrapper(callback, this));

        mCasoUsoAgendador.executa(new Runnable() {
            @Override
            public void run() {
                casoUso.run();
            }
        });
    }

    public <V extends CasoUso.respostaValor> void notificaResposta(final V resposta,
                                   final CasoUso.CasoUsoCallback<V> casoUsoCallback) {
        mCasoUsoAgendador.notificaResposta(resposta, casoUsoCallback);
    }

    private <V extends CasoUso.respostaValor> void notificaErro(
            final CasoUso.CasoUsoCallback<V> casoUsoCallback) {
        mCasoUsoAgendador.onErro(casoUsoCallback);
    }

    private static final class UiCallbackWrapper<V extends CasoUso.respostaValor> implements
            CasoUso.CasoUsoCallback<V> {

        private final CasoUso.CasoUsoCallback<V> mCallback;
        private final CasoUsoHandler mCasoUsoHandler;

        public UiCallbackWrapper(CasoUso.CasoUsoCallback<V> callback,
                                 CasoUsoHandler casoUsoHandler){
            mCallback = callback;
            mCasoUsoHandler = casoUsoHandler;
        }

        @Override
        public void onSucesso(V resposta) {
            mCasoUsoHandler.notificaResposta(resposta, mCallback);
        }

        @Override
        public void onErro() {
            mCasoUsoHandler.notificaErro(mCallback);
        }
    }

    public static CasoUsoHandler getInstancia() {
        if(INSTANCIA == null) {
            INSTANCIA = new CasoUsoHandler(new CasoUsoThreadPoolAgendador());
        }
        return INSTANCIA;
    }
}
