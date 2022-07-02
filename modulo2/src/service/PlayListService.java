package service;

import exceptions.BancoDeDadosException;
import models.Ouvinte;
import models.Playlist;
import repository.PlayListRepository;

import java.util.ArrayList;
import java.util.List;
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

    public void ListarPlayList(Ouvinte ouvinte) {
            try {
                List<Playlist> playlist= playListRepository.listarPorUsuario(ouvinte);

            } catch (BancoDeDadosException e) {
                System.out.println("Error ao buscar o banco de dados.");
            }
    }

}
