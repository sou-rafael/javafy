package models;

import abstracts.Conta;

import java.util.List;

public class Ouvinte extends User{
    private List <Conta> playlists;

    public List<Conta> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Conta> playlists) {
        this.playlists = playlists;
    }

    @Override
    public String toString() {
        return "Ouvinte{" +
                "playlists=" + playlists +
                '}';
    }

    public boolean criarPlayList(PlayList playList) {
        return;
    }
}
