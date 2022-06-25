package interfaces;

import java.util.ArrayList;

public interface CrudPlaylist {

    boolean atualizar (String musica);
    boolean deletarPlayList ();
    boolean removerMusica (String musica);
    void adicionar (String musica);
    void listar ();

}
