package com.projetolp3.data.local.CRUDS;

import android.support.annotation.NonNull;

import com.projetolp3.domain.model.ModeloPet;

import java.util.List;

public interface PetCRUD {
    interface CarregaPetsCallback {
        void onPetsCarregados(List<ModeloPet> PetModelo);
        void onDadosNaoDisponiveis();
    }
    interface GetPetCallback {
        void onPetCarregado(ModeloPet PetModelo);
        void onDadosNaoDisponiveis();
    }
    void getPets(@NonNull PetCRUD.CarregaPetsCallback callback);
    void getPet(int PetId, @NonNull PetCRUD.GetPetCallback callback);
    void salvaPet(@NonNull ModeloPet usuarioModelo);
    void deletaPets();
    void deletaPet(int PetId);
}
