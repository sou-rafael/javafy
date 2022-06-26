package interfaces;

import abstracts.User;
import models.Musica;
import utils.models.PlayListUtils;

import java.util.ArrayList;

public interface PlayListCrud<T> {

    boolean criar(T playlist);

    boolean atualizar(User user, String id);

    boolean deletar(Object object);

    PlayListUtils read(String id);

    boolean adicionarMusicas(User user, String id, Musica musicas);

}
