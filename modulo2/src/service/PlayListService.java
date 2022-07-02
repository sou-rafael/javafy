package service;

import exceptions.BancoDeDadosException;
import models.Ouvinte;
import models.Playlist;
import repository.ListaDeMusicaRepository;
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

    public List<Playlist> ListarPlayList(Ouvinte ouvinte) {
            try {
                List<Playlist> playlists = playListRepository.listar(ouvinte);
                for(Playlist playlist: playlists){
                    System.out.format("%-6s %-1s", "ID: " + playlist.getIdPlaylist(),
                            " | Nome: " + playlist.getNome() + "\n");
                }
                return playlists;

            } catch (BancoDeDadosException e) {
                System.out.println("Error ao buscar o banco de dados.");
                return new ArrayList<Playlist>();
            }
    }

    public Playlist getPlayList(Integer idPlaylist, Ouvinte ouvinte) {
        MusicaService musicaService =new MusicaService();
        try {
            Playlist playlist = playListRepository.getPlaylistById(idPlaylist, ouvinte);
            return playlist;
        }catch (BancoDeDadosException e) {
            System.out.println("Error ao editar playlist");
            return null;
        }
    }

    public void editarPlayList(Integer idPlayList, Ouvinte ouvinte){
        Playlist playlist = getPlayList(idPlayList, ouvinte);
        if(playlist != null) {
        }
    }

}
