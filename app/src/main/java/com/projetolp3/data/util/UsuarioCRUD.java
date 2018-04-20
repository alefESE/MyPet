package com.projetolp3.data.util;

import com.projetolp3.domain.model.ModeloUsuario;
import android.support.annotation.NonNull;
import java.util.List;

/**
 * Created by alef_ on 29/03/2018.
 */

/**
 * Principal ponto de entrada para acessar dados de Usuarios
 * Por simplicidade, apenas getUsuarios () e getUsuario () possuem callbacks.
 * Considere adicionar callbacks a outros métodos para informar o usuário de erros
 * de rede / banco de dados ou operações bem-sucedidas. Por exemplo, quando um novo Usuario
 * é criado, ele é armazenado de forma síncrona no cache, mas geralmente todas
 * as operações no banco de dados ou na rede devem ser executadas em um thread diferente.
 */

public interface UsuarioCRUD {

    interface CarregaUsuarioCallback {
        void onUsuariosCarregados(List<ModeloUsuario> usuarioModelo);
        void onDadosNaoDisponiveis();
    }
    interface GetUsuarioCallback {
        void onUsuarioCarregado(ModeloUsuario usuarioModelo);
        void onDadosNaoDisponiveis();
    }
    void getUsuarios(@NonNull CarregaUsuarioCallback callback);
    void getUsuario(@NonNull String usuarioId, @NonNull GetUsuarioCallback callback);
    void salvaUsuario(@NonNull ModeloUsuario usuarioModelo);
    void deletaUsuarios();
    void deleteUsuario(@NonNull String usuarioId);
}