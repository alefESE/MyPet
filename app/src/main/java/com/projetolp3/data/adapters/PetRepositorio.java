package com.projetolp3.data.adapters;

import android.support.annotation.NonNull;

import com.projetolp3.data.util.CRUDs.PetCRUD;
import com.projetolp3.data.local.PetFonteDados;
import com.projetolp3.domain.model.ModeloPet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PetRepositorio implements PetCRUD {
    private static PetRepositorio INSTANCIA = null;

    private final PetFonteDados mPetFonteDados;

    private final PetFonteDados mPetFonteDadosRemoto;

    Map<Integer, ModeloPet> mCachedPet;

    boolean mCachedSujo = false;

    private PetRepositorio(@NonNull PetFonteDados PetFonteDadosRemoto,
                                @NonNull PetFonteDados PetFonteDados) {
        mPetFonteDadosRemoto = PetFonteDadosRemoto;
        mPetFonteDados = PetFonteDados;
    }

    public static PetRepositorio getInstancia(PetFonteDados PetFonteDadosRemoto,
                                                   PetFonteDados PetFonteDados) {
        if(INSTANCIA == null){
            INSTANCIA = new PetRepositorio(PetFonteDadosRemoto, PetFonteDados);
        }
        return INSTANCIA;
    }

    public static void destroiInstancia() {
        INSTANCIA = null;
    }

    @Override
    public void getPets(@NonNull final PetCRUD.CarregaPetsCallback callback) {
        if(mCachedPet != null && !mCachedSujo){
            callback.onPetsCarregados(new ArrayList<>(mCachedPet.values()));
            return;
        }

        if(mCachedSujo) {
            getPetsFonteDadosRemoto(callback);
        } else {
            mPetFonteDados.getPets(new PetCRUD.CarregaPetsCallback() {
                @Override
                public void onPetsCarregados(List<ModeloPet> PetModelo) {
                    refrescaCache(PetModelo);
                    callback.onPetsCarregados(new ArrayList<>(mCachedPet.values()));
                }

                @Override
                public void onDadosNaoDisponiveis() {
                    getPetsFonteDadosRemoto(callback);
                }
            });
        }
    }


    @Override
    public void getPet(final int PetId, @NonNull final PetCRUD.GetPetCallback callback) {
        ModeloPet cachedPet = getPetComId(PetId);

        if(cachedPet != null){
            callback.onPetCarregado(cachedPet);
            return;
        }

        mPetFonteDados.getPet(PetId, new PetCRUD.GetPetCallback() {
            @Override
            public void onPetCarregado(ModeloPet PetModelo) {
                if(mCachedPet == null){
                    mCachedPet = new LinkedHashMap<>();
                }
                mCachedPet.put(PetModelo.getId(), PetModelo);
                callback.onPetCarregado(PetModelo);
            }

            @Override
            public void onDadosNaoDisponiveis() {
                mPetFonteDadosRemoto.getPet(PetId, new PetCRUD.GetPetCallback() {
                    @Override
                    public void onPetCarregado(ModeloPet PetModelo) {
                        if(mCachedPet == null){
                            mCachedPet = new LinkedHashMap<>();
                        }
                        mCachedPet.put(PetModelo.getId(), PetModelo);
                        callback.onPetCarregado(PetModelo);
                    }

                    @Override
                    public void onDadosNaoDisponiveis() {
                        callback.onDadosNaoDisponiveis();
                    }
                });
            }
        });
    }

    @Override
    public void salvaPet(@NonNull ModeloPet PetModelo) {
        mPetFonteDadosRemoto.salvaPet(PetModelo);
        mPetFonteDados.salvaPet(PetModelo);

        if(mCachedPet == null){
            mCachedPet = new LinkedHashMap<>();
        }
        mCachedPet.put(PetModelo.getId(), PetModelo);
    }

    @Override
    public void deletaPets() {
        mPetFonteDadosRemoto.deletaPets();
        mPetFonteDados.deletaPets();

        if(mCachedPet == null){
            mCachedPet = new LinkedHashMap<>();
        }
        mCachedPet.clear();
    }

    @Override
    public void deletaPet(int PetId) {
        mPetFonteDados.deletaPet(PetId);
        mPetFonteDadosRemoto.deletaPet(PetId);

        mCachedPet.remove(PetId);
    }

    @Override
    public void refrescaPet() {
        mCachedSujo = true;
    }

    private void refrescaCache(List<ModeloPet> Pets) {
        if(mCachedPet == null) {
            mCachedPet = new LinkedHashMap<>();
        }

        mCachedPet.clear();
        for(ModeloPet Pet : Pets) {
            mCachedPet.put(Pet.getId(), Pet);
        }
        mCachedSujo = false;
    }

    private void refrescaFonteDados(List<ModeloPet> Pets) {
        mPetFonteDados.deletaPets();
        for(ModeloPet Pet : Pets) {
            mPetFonteDados.salvaPet(Pet);
        }
    }

    public void getPetsFonteDadosRemoto(@NonNull final PetCRUD.CarregaPetsCallback callback) {
        mPetFonteDadosRemoto.getPets(new PetCRUD.CarregaPetsCallback() {
            @Override
            public void onPetsCarregados(List<ModeloPet> PetModelo) {
                refrescaCache(PetModelo);
                refrescaFonteDados(PetModelo);
                callback.onPetsCarregados(new ArrayList<>(mCachedPet.values()));
            }

            @Override
            public void onDadosNaoDisponiveis() {
                callback.onDadosNaoDisponiveis();
            }
        });
    }

    private ModeloPet getPetComId(int id){
        if(mCachedPet == null || mCachedPet.isEmpty()) {
            return null;
        }else{
            return mCachedPet.get(id);
        }
    }
}
