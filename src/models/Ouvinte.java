package models;

import abstracts.Conta;

import java.util.ArrayList;
import java.util.List;

public class Ouvinte extends User{

    private List <Conta> playlists = new ArrayList<>();

    public Ouvinte(String nome, Integer id, String dataDeNascimento, String genero,
                   boolean premium) {
        super(nome, id, dataDeNascimento, genero, premium);
    }

    public List<Conta> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Conta> playlists) {
        if(playlists != null){
            this.playlists = playlists;
        }

    }

    @Override
    public String toString() {
        return "Ouvinte{" +
                "playlists=" + playlists +
                '}';
    }

    public boolean criarPlayList(Playlist playList) {
        return false;
    }
}
