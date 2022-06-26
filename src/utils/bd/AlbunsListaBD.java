package utils.bd;

import abstracts.User;
import interfaces.PlayListCrud;
import models.Album;
import models.Musica;
import models.Playlist;
import utils.models.AlbunsListUtils;
import utils.models.PlayListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Nem todos os métodos foram implementados porque não foi usado o Artista como principal
 */

public class AlbunsListaBD implements PlayListCrud<Album> {

    private Map<String, AlbunsListUtils> albunsListMap = new HashMap<>();

    public Map<String, AlbunsListUtils> getAlbunsListMap() {
        return albunsListMap;
    }

    public void setAlbunsListMap(Map<String, AlbunsListUtils> albunsListMap) {
        this.albunsListMap = albunsListMap;
    }

    @Override
    public boolean criar(Album playlist) {
        if(!playlist.getPropietario().isPremium()){
            return false;
        }
        AlbunsListUtils albunsListUtils = new AlbunsListUtils(playlist);
        albunsListUtils.getUsers().add(playlist.getPropietario());
        albunsListMap.put(playlist.getId(), albunsListUtils);
        return true;
    }

    @Override
    public void atualizar(User user, String id, String nome) {
        // Não implementado porque não foi feito a lógica para artista
    }

    @Override
    public boolean remover(User user, String idMusica, String idPlaylist) {
        // Não implementado porque não foi feito a lógica para artista
        return false;
    }

    @Override
    public void deletar(Playlist playlist) {
        // Não implementado porque não foi feito a lógica para artista
    }


    @Override
    public PlayListUtils read(String id) {
        // Não implementado porque não foi feito a lógica para artista
        return null;
    }

    public ArrayList<User> listar () {
        return (ArrayList<User>) (ArrayList) albunsListMap.values().stream()
                .map(albunsListUtils -> albunsListUtils.getPlaylist().getPropietario())
                .collect(Collectors.toList());
    }

    @Override
    public boolean adicionarMusicas(User user, String id, Musica musicas) {
        return false;
    }
}
