package com.projetolp3.data.local;

import com.projetolp3.domain.model.ModeloUsuario;
import android.support.annotation.NonNull;
import java.util.List;

/**
 * Data Access Object(DAO) para a tabela de usuarios.
 * SQLite, room, etc.
 */
public interface UsuarioDAO {
    /**
     * Seleciona tados os usuarios.
     *
     * @return todos os usuarios.
     */

    List<ModeloUsuario> getUsuarios();

    /**
     * Seleciona Usuario por id.
     *
     * @param usuarioId o id do usuario.
     * @return o usuario com o id.
     */
    ModeloUsuario getUsuarioById(@NonNull String usuarioId);

    /**
     * Insere um usuario no banco. Se ele ja existe, substitui.
     *
     * @param usuarioModelo usuario a ser inserido.
     */
    void insertUsuariio(@NonNull ModeloUsuario usuarioModelo);

    /**
     * Atualiza usuario.
     *
     * @param usuarioModelo usuario a ser atualizado
     * @return o numero de usuarios a serem atualizados. Deve sempre ser 1.
     */
    int atualizaUsuario(@NonNull ModeloUsuario usuarioModelo);

    /**
     * Deleta usuario por id.
     *
     * @return o id do usuario deletado. Deve ser 1.
     */
    int deletaUsuarioById(@NonNull String usuarioId);

    /**
     * Deleta todos os usuarios.
     */
    void deletaUsuarios();
}