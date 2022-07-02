package models;

import abstracts.PlayListModel;

public class Playlist extends PlayListModel<Playlist> {

    public Playlist() {
    }

    public Playlist(Integer id_playlist, String nome, Playlist proprietario) {
        super(id_playlist, nome, proprietario);
    }
}
