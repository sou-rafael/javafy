package service;

import exceptions.BancoDeDadosException;
import models.Musica;
import models.Playlist;
import repository.ListaDeMusicaRepository;

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

}
