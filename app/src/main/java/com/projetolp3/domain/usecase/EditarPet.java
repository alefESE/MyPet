package com.projetolp3.domain.usecase;

import android.support.annotation.NonNull;

import com.projetolp3.data.adapters.PetRepositorio;
import com.projetolp3.domain.adapters.CasoUso;
import com.projetolp3.domain.model.ModeloPet;

public class EditarPet extends CasoUso<EditarPet.RequisicaoValores, EditarPet.RespostaValor> {

    private final PetRepositorio mPetRepositorio;

    public EditarPet(@NonNull PetRepositorio petRepositorio) {
        mPetRepositorio = petRepositorio;
    }

    @Override
    protected void executaCasoUso(EditarPet.RequisicaoValores requisicaoValores) {
        ModeloPet pet = requisicaoValores.getModeloPet();
        mPetRepositorio.salvaPet(pet);
        getmCasoUsoCallback().onSucesso(new EditarPet.RespostaValor(pet));
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
        private final ModeloPet mPet;

        public RespostaValor(@NonNull ModeloPet pet){
            mPet = pet;
        }

        public ModeloPet getPet(){
            return mPet;
        }
    }
}