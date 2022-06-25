package interfaces;

import abstracts.User;
import models.Playlist;

public interface PlayListBD {

    boolean criar(Playlist playlist, User user);

    boolean atualizar(Object object);

    boolean deletar(Object object);

    Playlist read(String id);
}
