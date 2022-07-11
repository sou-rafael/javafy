package br.com.javafy.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PlayList extends PlayListModel<Ouvinte> {
    public boolean validarSeMusicaJaEstaNaPlayList(Integer idMusica) {
        for (Musica musica : getMusicas()) {
            if (musica.getIdMusica().equals(idMusica)) {
                return true;
            }
        }
        return false;
    }

    public void imprimirPlayList() {
        System.out.format("%-6s %-1s", "ID: " + getIdPlaylist(), " | Nome: " + getNome() + "\n");
    }

}
