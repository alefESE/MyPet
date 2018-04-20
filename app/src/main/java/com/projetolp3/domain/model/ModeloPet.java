package com.projetolp3.domain.model;

import android.media.Image;
import android.arch.persistence.room.*;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "Pets")
public final class ModeloPet {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "raca")
    private String raca;
    @ColumnInfo(name = "sexo")
    private String sexo;
    @ColumnInfo(name = "aniversario")
    private Date aniversario;
    @ColumnInfo(name = "foto")
    private Image foto;

    public ModeloPet(String nome, String raca, String sexo, Date aniversario, Image foto) {
        this.nome = nome;
        this.raca = raca;
        this.sexo = sexo;
        this.aniversario = aniversario;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getAniversario() {
        return aniversario;
    }

    public void setAniversario(Date aniversario) {
        this.aniversario = aniversario;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }
}
