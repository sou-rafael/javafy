package service;

import exceptions.BancoDeDadosException;
import models.Ouvinte;
import models.Playlist;
import repository.PlayListRepository;

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

    public void editarNomePlaylist(Playlist playlist, String novoNome) {
        try {
            String nomeAntigo = playlist.getNome();
            playlist.setNome(novoNome);
            boolean editarNomePlaylist = playListRepository.editar(playlist.getIdPlaylist(), playlist);
            if(editarNomePlaylist) {
                Menus.imprimirBlue("Nome da playlist editada com sucesso!");
            } else {
                playlist.setNome(nomeAntigo);
                Menus.imprimirRed("Não foi possível editar a playlist!");
            }
        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("Erro ao editar a playlist.");
        }
    }

    public void deletarPlaylist(Playlist playlist){
        try {
            boolean remover = playListRepository.remover(playlist.getIdPlaylist());
            if(remover){
                Menus.imprimirBlue("Playlist deletada com sucesso!");
            } else {
                Menus.imprimirRed("Não foi possível deletar playlist.");
            }
        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("Error ao deletar playlist.");
        }
    }
}
