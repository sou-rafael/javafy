package models;

import abstracts.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ouvinte extends User {

    private ArrayList<String> playlists = new ArrayList<>();

    public Ouvinte(String nome, Integer id, String dataDeNascimento, String genero,
                   boolean premium) {
        super(nome, id, dataDeNascimento, genero, premium);
    }

    public List<String> getPlaylists() {
        return playlists;
    }



    public boolean removerPlayList(Playlist playlist){
        if( playlist != null ){
            return playlists.remove(playlist.getId());
        } return false;
    }

    public boolean deletarPlaylist(Playlist playlist){
        if(playlist != null && getId().equals(playlist.getPropietario().getId())){
            boolean b = playlists.remove(playlist.getId());
            return true;
        }
        return false;
    }


    public boolean criarPlayList(Playlist playlist) {
        if(playlist != null && isPremium() && ehProprietarioDaPlaylist(playlist)) {
            playlists.add(playlist.getId());
            return true;
        }
        return false;
    }

    boolean ehProprietarioDaPlaylist(Playlist playlist){
        return playlist.getPropietario().getId().equals(getId());
    }

    @Override
    public String toString() {
        return "Ouvinte{" +
                "playlists=" + playlists +
                '}';
    }
}
