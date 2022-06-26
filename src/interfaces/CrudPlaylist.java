package interfaces;

import abstracts.User;
import models.Musica;

import java.util.ArrayList;

public interface CrudPlaylist {

    boolean atualizarNomePlayList (String novoNome);
    boolean deletarPlayList ();
    boolean removerMusica (Musica musica, User userPlayList);
    void adicionar (ArrayList<Musica> musicas);
    void listar ();

}
