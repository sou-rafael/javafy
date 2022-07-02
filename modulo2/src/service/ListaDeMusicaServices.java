package service;

import exceptions.BancoDeDadosException;
import models.Musica;
import models.Ouvinte;
import models.Playlist;
import repository.ListaDeMusicaRepository;

import java.util.List;

public class ListaDeMusicaServices {

    ListaDeMusicaRepository listaDeMusicaRepository;

    public ListaDeMusicaServices(){
        listaDeMusicaRepository = new ListaDeMusicaRepository();
    }


    public void salvarPlaylistComMusica(Playlist playlist, Musica musica) {
        try {
            listaDeMusicaRepository.adicionar(playlist, musica);
        } catch (BancoDeDadosException e) {
            System.out.println("Ops! Algum erro aconteceu ao salvar a playlist");
        }

    }

    public boolean verificarSeAMusicaEstaNaPlayList(Musica musica, Playlist playlist) {
        try {
            return listaDeMusicaRepository.
                    getMusicaInPlayList(playlist.getIdPlaylist(), musica.getIdMusica());
        } catch (BancoDeDadosException e) {
            System.out.println("Erro internao.");
            return false;
        }
    }

    public void adicionarMusicaNaPlayList(Playlist playlist, Integer idMusicaUser,
                                          MusicaService musicaService) {
        Musica musica = musicaService.getMusica(idMusicaUser);
        if(musica != null) {
            boolean musicaJaNaPlayList =
                    verificarSeAMusicaEstaNaPlayList(musica, playlist);
            if(!musicaJaNaPlayList) {
                salvarPlaylistComMusica(playlist, musica);
            } else {
                System.out.println("Musica já está na playlist" );
            }
        } else {
            System.out.println("OPS! ID INFORMADO INVÁLIDO");
        }
    }

    public Playlist getMusicasWithPlayList(Integer id, Ouvinte ouvinte){
        PlayListService playListService = new PlayListService();
        // Aqui já garante que a musica é do usuário
        Playlist playlist = playListService.getPlayList(id, ouvinte);
        if(playlist != null) {
            try {
                List<Musica> musicas = listaDeMusicaRepository.getMusicaInPlayList(playlist);
                playlist.getMusicas().clear();
                playlist.setMusicas(musicas);
                return playlist;
            } catch (BancoDeDadosException e) {
                e.getMessage();
                return null;
            }
        }
        return null;
    }

    public void deletarMusicaDaPlayList(Playlist playlist, Ouvinte ouvinte, Integer idMusica){
        if(playlist.getProprietario().getIdOuvinte().equals(ouvinte.getIdOuvinte())){
            try {
                boolean deletar= listaDeMusicaRepository.removerMusicaDaPlayList(idMusica, playlist);
                if(deletar){
                    System.out.println("Musica deletada com sucesso da playlist!");
                    return;
                } else {
                    System.out.println("Id da musica é inválida. Digite corretamente!");
                    return;
                }
            } catch (BancoDeDadosException e) {
                System.out.println("Error a deletar a musica da playlist " + playlist.getNome());
            }
        }
        System.out.println("Playlist deve ser do usuário!");
    }

}
