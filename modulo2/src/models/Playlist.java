package models;

import abstracts.PlayListModel;

public class Playlist extends PlayListModel<Ouvinte> {

    public Playlist() {
    }

    public Playlist(Integer idPlaylist, String nome, Ouvinte proprietario) {
        super(idPlaylist, nome, proprietario);
    }

    public boolean validarSeMusicaJaEstaNaPlayList(Integer idMusica) {
        for (Musica musica: getMusicas()) {
            if(musica.getIdMusica().equals(idMusica)) {
                return true;
            }
        }
        return false;
    }

    public void imprimirPlayList(){
        System.out.format("%-6s %-1s", "ID: " + getIdPlaylist(), " | Nome: " + getNome() + "\n");
    }

}
