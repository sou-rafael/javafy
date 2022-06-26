package utils.bd;

import abstracts.User;
import interfaces.PlayListCrud;
import models.Musica;
import models.Playlist;
import utils.models.PlayListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayListsBD implements PlayListCrud<Playlist> {

    Map<String, PlayListUtils> playlistMap = new HashMap<>();

    public Map<String, PlayListUtils> getPlaylistMap() {
        return playlistMap;
    }

    public void setPlaylistMap(Map<String, PlayListUtils> playlistMap) {
        this.playlistMap = playlistMap;
    }

    @Override
    public boolean criar(Playlist playlist){
        if(!playlist.getPropietario().isPremium()){
           return false;
        }
        PlayListUtils playListUtils = new PlayListUtils(playlist);
        playListUtils.getUsers().add(playlist.getPropietario());
        playlistMap.put(playlist.getId(), playListUtils);
        return true;
    };

    @Override
    public boolean atualizar(User user, String id) {
        return false;
    }

    @Override
    public boolean deletar(Object object) {
        return false;
    }

    @Override
    public PlayListUtils read(String id) {
        if(playlistMap.containsKey(id)){
            return playlistMap.get(id);
        }
        return null;
    }

    @Override
    public boolean adicionarMusicas(User user, String id, Musica musicas) {
        if(!playlistMap.containsKey(id) ){
            return false;
        }
        playlistMap.get(id).getPlaylist().getListaMusicas().add(musicas);
        return true;
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
