package com.projetolp3.domain.usecase;

import android.support.annotation.NonNull;

import com.projetolp3.data.adapters.PetRepositorio;
import com.projetolp3.data.util.CRUDs.PetCRUD;
import com.projetolp3.domain.adapters.CasoUso;
import com.projetolp3.domain.model.ModeloPet;

import java.util.ArrayList;
import java.util.List;

public class CarregaPets extends CasoUso<CarregaPets.RequisicaoValores, CarregaPets.RespostaValor> {

    private final PetRepositorio mPetRepositorio;

    public CarregaPets(@NonNull PetRepositorio PetRepositorio) {
        mPetRepositorio = PetRepositorio;
    }

    @Override
    protected void executaCasoUso(CarregaPets.RequisicaoValores requisicaoValores) {

        mPetRepositorio.getPets(new PetCRUD.CarregaPetsCallback() {
            @Override
            public void onPetsCarregados(List<ModeloPet> PetModelo) {
                List<ModeloPet> Pets = new ArrayList<>(PetModelo);
                CarregaPets.RespostaValor respostaValor = new CarregaPets.RespostaValor(Pets);
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
        private final List<ModeloPet> mPets;

        public RespostaValor(@NonNull List<ModeloPet> Pets) {
            mPets = Pets;
        }

        public List<ModeloPet> getPets() {
            return mPets;
        }
    }
}
