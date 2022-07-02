package service;

import exceptions.BancoDeDadosException;
import models.Playlist;
import repository.PlayListRepository;
/*
    Essa classe serve para adicionar no banco de dados os
    ids da playlist e suas respectivas músicas
 */

public class PlayListService {
    private PlayListRepository playListRepository;

    public PlayListService() {
        this.playListRepository = new PlayListRepository();
    }

    public Playlist adicionarPlaylist(Playlist playlist) throws BancoDeDadosException {
            Playlist playlist1 = playListRepository.adicionar(playlist);
            return playlist;
    }

}
