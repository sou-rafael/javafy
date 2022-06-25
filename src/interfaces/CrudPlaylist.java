package interfaces;

import models.Musica;

public interface CrudPlaylist {

    boolean atualizar (Musica musica);
    boolean deletarPlayList ();
    boolean removerMusica (Musica musica);
    void adicionar (Musica musica);
    void listar ();

}
