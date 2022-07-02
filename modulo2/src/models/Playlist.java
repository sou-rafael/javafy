package models;

import abstracts.PlayListModel;

public class Playlist extends PlayListModel<Ouvinte> {

    public Playlist() {
    }

    public Playlist(Integer idPlaylist, String nome, Ouvinte proprietario) {
        super(idPlaylist, nome, proprietario);
    }
}
