package com.projetolp3.data.util.CRUDs;

import android.support.annotation.NonNull;

import com.projetolp3.domain.model.ModeloPet;

import java.util.List;

public interface PetCRUD {

    void refrescaPet();

    interface CarregaPetsCallback {
        void onPetsCarregados(List<ModeloPet> petModelo);
        void onDadosNaoDisponiveis();
    }
    interface GetPetCallback {
        void onPetCarregado(ModeloPet petModelo);
        void onDadosNaoDisponiveis();
    }
    void getPets(@NonNull PetCRUD.CarregaPetsCallback callback);
    void getPet(int PetId, @NonNull PetCRUD.GetPetCallback callback);
    void salvaPet(@NonNull ModeloPet petModelo);
    void deletaPets();
    void deletaPet(int PetId);
}
