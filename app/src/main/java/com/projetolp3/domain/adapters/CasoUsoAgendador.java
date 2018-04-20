package com.projetolp3.domain.adapters;

public interface CasoUsoAgendador {

    void executa(Runnable runnable);

    <V extends CasoUso.respostaValor> void notificaResposta(final V resposta,
                                                            final CasoUso.CasoUsoCallback<V> casoUsoCallback);

    <V extends CasoUso.respostaValor> void onErro(
            final CasoUso.CasoUsoCallback<V> casoUsoCallback);
}
