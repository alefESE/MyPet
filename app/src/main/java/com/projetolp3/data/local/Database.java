package com.projetolp3.data.local;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.projetolp3.data.local.DAOs.LembreteDAO;
import com.projetolp3.domain.model.ModeloLembrete;
import com.projetolp3.domain.model.ModeloPet;

@android.arch.persistence.room.Database(entities = {ModeloLembrete.class, ModeloPet.class}, version = 1)
public abstract class Database extends RoomDatabase {
    private static Database INSTANCIA;

    public abstract LembreteDAO lembreteDAO();

    private static final Object sLock = new Object();

    public static Database getInstance(Context contexto){
        synchronized (sLock){
            if(INSTANCIA == null){
                INSTANCIA = Room.databaseBuilder(contexto.getApplicationContext(),
                        Database.class, "MyPet.db")
                        .build();
            }
            return INSTANCIA;
        }
    }

}
