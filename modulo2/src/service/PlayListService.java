package service;

import exceptions.BancoDeDadosException;
import models.Ouvinte;
import models.Playlist;
import repository.PlayListRepository;
import views.Menus;

import java.util.ArrayList;
import java.util.List;

public class PlayListService {
    private final PlayListRepository playListRepository;

    public PlayListService() {
        this.playListRepository = new PlayListRepository();
    }

    public void adicionarPlaylist(Playlist playlist){
        try {
            playlist = playListRepository.adicionar(playlist);
            Menus.imprimirBlue("Playlist criada com sucesso!");
        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("Error ao criar playlist.");
        }
    }

    // Retorna uma lista de playlist do ouvinte e imprime os dados.
    public List<Playlist> ListarPlayList(Ouvinte ouvinte) {
        try {
            List<Playlist> playlists = playListRepository.listar(ouvinte);
            for(Playlist playlist: playlists){
                playlist.imprimirPlayList();
            }
            return playlists;
        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("Error ao buscar o banco de dados.");
            return new ArrayList<Playlist>();
        }
    }

    // Retorna uma playlist
    public Playlist getPlayList(Integer idPlaylist, Ouvinte ouvinte) {
        MusicaService musicaService =new MusicaService();
        try {
            return playListRepository.getPlaylistById(idPlaylist, ouvinte);
        }catch (BancoDeDadosException e) {
            Menus.imprimirRed("Error ao editar playlist.");
            return null;
        }
    }

}
