package com.projetolp3.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.projetolp3.domain.Tipos;

import java.sql.Time;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import java.util.Date;

@Entity(tableName = "Lembretes")
public final class ModeloLembrete {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "data")
    private String data;
    @ColumnInfo(name = "tipo")
    @Tipos
    private int tipo;
    @ColumnInfo(name = "horario")
    private String horario;
    @ColumnInfo(name = "local")
    private String local;
    @ColumnInfo(name = "duracao")
    private long duracao;
    @ColumnInfo(name = "hr_notificacao")
    private String hr_notificacao;
    @ColumnInfo(name = "Nota")
    private String nota;

    public ModeloLembrete(String data, @Tipos int tipo, String horario, String local, long duracao,
                          String hr_notificacao, @Nullable String nota) {
        this.id = id;
        this.data = data;
        this.tipo = tipo;
        this.horario = horario;
        this.local = local;
        this.duracao = duracao;
        this.hr_notificacao = hr_notificacao;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData(){
        return data;
    }

    public void setData(Date data){
        this.data = DateFormat.format("dd/MM/yyyy", data).toString();
    }

    @Tipos
    public int getTipo() {
        return tipo;
    }

    public void setTipo(@Tipos int tipo) {
        this.tipo = tipo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public long getDuracao() {
        return duracao;
    }

    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }

    public String getHr_notificacao() {
        return hr_notificacao;
    }

    public void setHr_notificacao(String hr_notificacao) {
        this.hr_notificacao = hr_notificacao;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
