package models;

import abstracts.User;

import java.util.ArrayList;

public class Ouvinte extends User {

    private ArrayList<String> playlists = new ArrayList<>();

    public Ouvinte(String nome, String id, String dataDeNascimento, String genero,
                   boolean premium) {
        super(nome, id, dataDeNascimento, genero, premium);
    }

    public ArrayList<String> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<String> playlists) {
        if(playlists != null) {
            this.playlists = playlists;
        }
    }

    @Override
    public String toString() {
        return "Ouvinte{" +
                "playlists=" + playlists +
                '}';
    }
}
