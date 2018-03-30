package com.projetolp3.presenter.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projetolp3.domain.model.ModeloUsuario;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by alef_ on 29/03/2018.
 */

public class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.UsuarioViewSuporte> {

    public interface OnItemClickListener {
        void onUsuarioItemClicado(ModeloUsuario ModeloUsuario);
    }

    private List<ModeloUsuario> usuarioCollection;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;

    AdaptadorUsuario(Context contexto){
        this.layoutInflater =
                (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.usuarioCollection = Collections.emptyList();
    }


    @Override
    public AdaptadorUsuario.UsuarioViewSuporte onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AdaptadorUsuario.UsuarioViewSuporte suporte, int posicao) {
        final ModeloUsuario userModel = this.usuarioCollection.get(posicao);
        suporte.textViewTitulo.setText(userModel.getNomeCompleto());
        suporte.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (AdaptadorUsuario.this.onItemClickListener != null) {
                    AdaptadorUsuario.this.onItemClickListener.onUsuarioItemClicado(userModel);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return(this.usuarioCollection != null) ? this.usuarioCollection.size() : 0;
    }

    @Override
    public long getItemId(int posicao){
        return posicao;
    }

    public void setUsuarioCollection(Collection<ModeloUsuario> usuarioCollection){
        this.validarUsuarioCollection(usuarioCollection);
        this.usuarioCollection = (List<ModeloUsuario>) usuarioCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemCliclListener(OnItemClickListener onItemCliclListener){
        this.onItemClickListener = onItemCliclListener;
    }

    public void validarUsuarioCollection(Collection<ModeloUsuario> usuarioCollection){
        if(usuarioCollection == null)
            throw new IllegalArgumentException("A lista nao pode ser nula");
    }

    static class UsuarioViewSuporte extends RecyclerView.ViewHolder {
        TextView textViewTitulo;
        UsuarioViewSuporte(View itemView) {
            super(itemView);
            textViewTitulo.findViewById(itemView.getId());
        }
    }
}
