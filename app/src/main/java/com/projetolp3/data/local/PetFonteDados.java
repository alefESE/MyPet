package com.projetolp3.data.local;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.projetolp3.data.local.CRUDS.PetCRUD;
import com.projetolp3.data.local.DAOS.PetDAO;
import com.projetolp3.domain.model.ModeloPet;
import com.projetolp3.util.AppExecutors;

import java.util.List;

public class PetFonteDados implements PetCRUD {
    private static volatile PetFonteDados INSTANCIA;

    private PetDAO mPetDAO;

    private AppExecutors mAppExecutors;

    private PetFonteDados(@NonNull AppExecutors appExecutors,
                               @NonNull PetDAO PetDAO) {
        mAppExecutors = appExecutors;
        mPetDAO = PetDAO;
    }

    public static PetFonteDados getInstacia(@NonNull AppExecutors appExecutors,
                                                 @NonNull PetDAO PetDAO) {
        if(INSTANCIA == null) {
            synchronized (PetFonteDados.class) {
                if(INSTANCIA == null){
                    INSTANCIA = new PetFonteDados(appExecutors, PetDAO);
                }
            }
        }
        return INSTANCIA;
    }

    @Override
    public void getPets(@NonNull final PetCRUD.CarregaPetsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ModeloPet> Pets = mPetDAO.getPets();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(Pets.isEmpty()){
                            callback.onDadosNaoDisponiveis();
                        }else{
                            callback.onPetsCarregados(Pets);
                        }
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getPet(final int PetId, @NonNull final PetCRUD.GetPetCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final ModeloPet Pet = mPetDAO.getPetPorId(PetId);

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(Pet != null){
                            callback.onPetCarregado(Pet);
                        }else{
                            callback.onDadosNaoDisponiveis();
                        }
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void salvaPet(@NonNull final ModeloPet PetModelo) {
        //checkNotNull(PetModelo);
        Runnable salvaRunnable = new Runnable() {
            @Override
            public void run() {
                mPetDAO.inserePet(PetModelo);
            }
        };

        mAppExecutors.diskIO().execute(salvaRunnable);
    }

    @Override
    public void deletaPets() {
        Runnable deletaRunnable = new Runnable() {
            @Override
            public void run() {
                mPetDAO.deletaPets();
            }
        };

        mAppExecutors.diskIO().execute(deletaRunnable);
    }

    @Override
    public void deletaPet(final int PetId) {
        Runnable deletaRunnable = new Runnable() {
            @Override
            public void run() {
                mPetDAO.deletaPetPorId(PetId);
            }
        };

        mAppExecutors.diskIO().execute(deletaRunnable);
    }

    @VisibleForTesting
    static void limpaInstacia(){
        INSTANCIA = null;
    }
}
