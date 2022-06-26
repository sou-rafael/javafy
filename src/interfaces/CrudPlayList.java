package interfaces;

import abstracts.User;
import models.Musica;
import models.Playlist;

import java.util.ArrayList;

public interface CrudPlayList<T> {

    public boolean atualizarPlayList (T playlist);
    public boolean deletarPlayList (User user, T playlist);
    public boolean removerMusicaPlayList (Musica musica, User user, T playlist);
    public boolean criarPlayList ();
    public void readPlayList();
}
