package service;

import exceptions.BancoDeDadosException;
import models.Ouvinte;
import models.Playlist;
import repository.PlayListRepository;
import views.Menus;

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

    public void adicionarPlaylist(Playlist playlist){
            try {
                playlist = playListRepository.adicionar(playlist);
                System.out.println("Playlist criada com sucesso");
            } catch (BancoDeDadosException e) {
                System.out.println("Error ao criar playlist");
            }
    }

    public void ListarPlayList(Ouvinte ouvinte) {
            try {
                List<Playlist> playlists = playListRepository.listarPorUsuario(ouvinte);
                //ouvinte.getPlaylists().clear();
                //ouvinte.getPlaylists().addAll(playlists);
                for(Playlist playlist: playlists){
                    System.out.format("%1s%8s", "ID: " + playlist.getIdPlaylist(),
                            " | Nome: " + playlist.getNome() + "\n");
                }
            } catch (BancoDeDadosException e) {
                System.out.println("Error ao buscar o banco de dados.");
            }
    }
}
