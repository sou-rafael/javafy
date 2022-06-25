package interfaces;

import abstracts.Conta;
import models.Musica;

import java.util.ArrayList;

public interface CrudPlaylist {

    boolean atualizarNomePlayList (String novoNome);
    boolean deletarPlayList ();
    boolean removerMusica (Musica musica, Conta userPlayList);
    void adicionar (ArrayList<Musica> musicas);
    void listar ();

}
