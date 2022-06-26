package models;

import abstracts.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ouvinte extends User {

    private ArrayList<String> playlists = new ArrayList<>();

    public Ouvinte(String nome, String id, String dataDeNascimento, String genero,
                   boolean premium) {
        super(nome, id, dataDeNascimento, genero, premium);
    }

    public ArrayList<String> getPlaylists() {
        return playlists;
    }


}
