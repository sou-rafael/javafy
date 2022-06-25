package utils;

import abstracts.User;
import interfaces.PlayListBD;
import models.Playlist;

import java.util.ArrayList;

public class PlayListsBD implements PlayListBD {

    ArrayList<Playlist> playlists = new ArrayList<>();

    @Override
    public boolean criar(Playlist playlist, User user){
        boolean ehNulo = validarNull(playlist, user);
        if(ehNulo || !validarSeEProprietario(playlist, user) || !user.isPremium()){
           return false;
        }
        playlists.add(playlist);
        return true;
    };

    @Override
    public boolean atualizar(Object object) {
        return false;
    }

    @Override
    public boolean deletar(Object object) {
        return false;
    }

    @Override
    public Playlist read(String id) {
        return null;
    }

    boolean validarSeEProprietario(Playlist playlist, User user){
        return playlist.getPropietario().getId().equals(user.getId());
    }

    boolean validarNull(Playlist playlist, User user){
        if(playlist == null || user == null){
            return true;
        }
        return false;
    }

}
