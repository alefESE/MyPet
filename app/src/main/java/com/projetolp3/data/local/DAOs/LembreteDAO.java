package com.projetolp3.data.local.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.projetolp3.domain.model.ModeloLembrete;

import java.util.List;

@Dao
public interface LembreteDAO {
    @Query("SELECT * FROM Lembretes")
    List<ModeloLembrete> getLembretes();

    @Query("SELECT * FROM Lembretes WHERE data LIKE :data")
    List<ModeloLembrete> getLembretesPorData(String data);

    @Query("SELECT * FROM Lembretes WHERE id LIKE :id")
    ModeloLembrete getLembretePorId(final int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insereLembrete(ModeloLembrete lembrete);

    @Update
    int atualizaLembrete(ModeloLembrete lembrete);

    @Query("DELETE FROM Lembretes WHERE id = :id")
    int deletaLembretePorId(int id);

    @Query("DELETE FROM Lembretes")
    void deletaLembretes();
}
