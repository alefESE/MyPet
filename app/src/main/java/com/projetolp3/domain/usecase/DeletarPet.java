package com.projetolp3.domain.usecase;

import android.support.annotation.NonNull;

import com.projetolp3.data.adapters.PetRepositorio;
import com.projetolp3.domain.adapters.CasoUso;

public class DeletarPet extends CasoUso<DeletarPet.RequisicaoValores, DeletarPet.RespostaValor> {

    private final PetRepositorio mPetRepositorio;

    public DeletarPet(@NonNull PetRepositorio petRepositorio) {
        mPetRepositorio = petRepositorio;
    }

    @Override
    protected void executaCasoUso(RequisicaoValores requisicaoValores) {
        int id = requisicaoValores.getId();
        mPetRepositorio.deletaPet(id);
        getmCasoUsoCallback().onSucesso(new RespostaValor());
    }

    public static final class RequisicaoValores implements CasoUso.requisicaoValores {
        private final int id;

        public RequisicaoValores(final int id){
            this.id = id;
        }

        public int getId(){
            return id;
        }
    }

    public static final class RespostaValor implements CasoUso.respostaValor {

    }
}
