package utils.bd;

import abstracts.User;
import interfaces.PlayListCrud;
import models.Musica;
import models.Playlist;
import utils.models.PlayListUtils;

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
    public void atualizar(User user, String id, String nome) {
        if(playlistMap.containsKey(id)){
            playlistMap.get(id).getPlaylist().setNomePlaylist(nome);
        }
    }

    @Override
    public boolean remover(User user, String idMusica, String idPlaylist) {
        if(playlistMap.containsKey(idPlaylist)) {
            playlistMap.get(idPlaylist).getPlaylist().getListaMusicas()
                    .removeIf(musica -> musica.getId().equals(idMusica));
            return true;
        }
        return false;
    }

    @Override
    public void deletar(Playlist playlist) {
        playlistMap.remove(playlist.getId());
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
        if(playlistMap.get(id).getPlaylist().getListaMusicas().contains(musicas)){
            return false;
        }
        playlistMap.get(id).getPlaylist().getListaMusicas().add(musicas);
        return true;
    }

    boolean validarSeEProprietario(Playlist playlist, User user){
        return playlist.getPropietario().getId().equals(user.getId());
    }

    boolean validarNull(Playlist playlist, User user){
        return playlist == null || user == null;
    }

}
