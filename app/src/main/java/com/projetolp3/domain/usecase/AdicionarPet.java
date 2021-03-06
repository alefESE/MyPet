package com.projetolp3.domain.usecase;

import android.support.annotation.NonNull;

import com.projetolp3.data.adapters.PetRepositorio;
import com.projetolp3.domain.adapters.CasoUso;
import com.projetolp3.domain.model.ModeloPet;

public class AdicionarPet extends CasoUso<AdicionarPet.RequisicaoValores, AdicionarPet.RespostaValor> {

    private final PetRepositorio mPetRepositorio;

    public AdicionarPet(@NonNull PetRepositorio petRepositorio) {
        mPetRepositorio = petRepositorio;
    }

    @Override
    protected void executaCasoUso(RequisicaoValores requisicaoValores) {
        ModeloPet pet = requisicaoValores.getModeloPet();
        mPetRepositorio.salvaPet(pet);
        getmCasoUsoCallback().onSucesso(new RespostaValor());
    }

    public static final class RequisicaoValores implements CasoUso.requisicaoValores {
        private final ModeloPet mModeloPet;

        public RequisicaoValores(@NonNull ModeloPet modeloPet) {
            mModeloPet = modeloPet;
        }

        public ModeloPet getModeloPet() {
            return mModeloPet;
        }
    }

    public static final class RespostaValor implements CasoUso.respostaValor {
    }
}
