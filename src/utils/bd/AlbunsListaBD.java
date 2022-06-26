package utils.bd;

import abstracts.PlayListAbstract;
import abstracts.User;
import interfaces.PlayListCrud;
import models.Album;
import models.Artista;
import models.Musica;
import utils.models.AlbunsListUtils;
import utils.models.PlayListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
    public boolean atualizar(User user, String id) {
        return false;
    }

    @Override
    public boolean deletar(Object object) {
        return false;
    }

    @Override
    public PlayListUtils read(String id) {

        return null;
    }

    public ArrayList<User> listar () {

        ArrayList <User> artistas = (ArrayList) albunsListMap.values().stream().map(albunsListUtils -> albunsListUtils.getPlaylist().getPropietario()).collect(Collectors.toList());


       return artistas;


    }

    @Override
    public boolean adicionarMusicas(User user, String id, Musica musicas) {
        return false;
    }
}
