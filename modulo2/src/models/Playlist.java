package models;

import abstracts.PlayListModel;

public class Playlist extends PlayListModel<Playlist> {

    public Playlist() {
    }

    public Playlist(Integer idPlaylist, String nome, Playlist proprietario) {
        super(idPlaylist, nome, proprietario);
    }
}
