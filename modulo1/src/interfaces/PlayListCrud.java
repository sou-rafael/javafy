package interfaces;

import abstracts.User;
import models.Musica;
import models.Playlist;
import utils.models.PlayListUtils;

public interface PlayListCrud<T> {

    boolean criar(T playlist);

    void atualizar(User user, String id, String nome);

    boolean remover(User user, String idMusica, String idPlaylist);

    void deletar(Playlist playlist);

    PlayListUtils read(String id);

    boolean adicionarMusicas(User user, String id, Musica musicas);

}
