package service;

import exceptions.BancoDeDadosException;
import models.Musica;
import models.Ouvinte;
import models.Playlist;
import repository.ListaDeMusicaRepository;
import views.Menus;

import java.util.List;

public class ListaDeMusicaServices {

    ListaDeMusicaRepository listaDeMusicaRepository;

    public ListaDeMusicaServices(){
        listaDeMusicaRepository = new ListaDeMusicaRepository();
    }

    // Esse método salva a musica no banco de dados
    public void salvarPlaylistComMusica(Playlist playlist, Musica musica) {
        try {
            listaDeMusicaRepository.adicionar(playlist, musica);
        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("Ops! Algum erro aconteceu ao salvar a playlist");
        }

    }

    // Verifica se a musica está no banco de dados
    public boolean verificarSeAMusicaEstaNaPlayList(Musica musica, Playlist playlist) {
        try {
            return listaDeMusicaRepository.
                    getMusicaInPlayList(playlist.getIdPlaylist(), musica.getIdMusica());
        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("Erro internao.");
            return false;
        }
    }

    // Verifica se a musica existe / Valida se já não está na playlist
    public void adicionarMusicaNaPlayList(Playlist playlist, Integer idMusicaUser,
                                          MusicaService musicaService) {
        Musica musica = musicaService.getMusica(idMusicaUser);
        if(musica != null) {
            boolean musicaJaNaPlayList =
                    verificarSeAMusicaEstaNaPlayList(musica, playlist);
            if(!musicaJaNaPlayList) {
                salvarPlaylistComMusica(playlist, musica);
            } else {
                Menus.imprimirRed("Musica já está na playlist.");
            }
        } else {
            Menus.imprimirRed("OPS! ID INFORMADO INVÁLIDO.");
        }
    }

    // Pega um playlist e busca todas as musicas adicionadas nela
    public Playlist getMusicasWithPlayList(Integer idPlaylist, Ouvinte ouvinte){
        PlayListService playListService = new PlayListService();
        // Aqui já garante que a musica é do usuário
        Playlist playlist = playListService.getPlayList(idPlaylist, ouvinte);
        if(playlist != null) {
            try {
                List<Musica> musicas = listaDeMusicaRepository.getMusicaInPlayList(playlist);
                playlist.getMusicas().clear();
                playlist.setMusicas(musicas);
                return playlist;
            } catch (BancoDeDadosException e) {
                return null;
            }
        }
        return null;
    }

    // Deleta uma musica da playlist / Ocorre validação do usuário e playlist
    public void deletarMusicaDaPlayList(Playlist playlist, Ouvinte ouvinte, Integer idMusica){
        if(playlist.getProprietario().getIdOuvinte().equals(ouvinte.getIdOuvinte())){
            try {
                boolean deletar= listaDeMusicaRepository.removerMusicaDaPlayList(idMusica, playlist);
                if(deletar){
                    Menus.imprimirBlue("Musica deletada com sucesso da playlist!");
                    return;
                } else {
                    Menus.imprimirRed("Id da musica é inválida. Digite corretamente!");
                    return;
                }
            } catch (BancoDeDadosException e) {
                Menus.imprimirRed("Error a deletar a musica da playlist " + playlist.getNome() + ".");
            }
        }
        Menus.imprimirRed("Playlist deve ser do usuário!");
    }

}
