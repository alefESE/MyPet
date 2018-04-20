package com.projetolp3.data.local.DAOS;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.projetolp3.domain.model.ModeloPet;

import java.util.List;

@Dao
public interface PetDAO {
    @Query("SELECT * FROM Pets")
    List<ModeloPet> getPets();

    @Query("SELECT * FROM Pets WHERE id = :id")
    ModeloPet getPetPorId(int id);

    @Query("SELECT * FROM Pets WHERE nome LIKE :nome")
    List<ModeloPet> getPetPorNome(String nome);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserePet(ModeloPet pet);

    @Update
    int atualizaPet(ModeloPet pet);

    @Query("DELETE FROM Pets WHERE id = :id")
    int deletaPetPorId(int id);

    @Query("DELETE FROM Pets")
    void deletaPets();
}
