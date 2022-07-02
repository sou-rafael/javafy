package service;

import exceptions.BancoDeDadosException;
import models.Musica;
import models.Playlist;
import repository.ListaDeMusicaRepository;

import java.sql.SQLException;

public class ListaDeMusicaServices {

    ListaDeMusicaRepository listaDeMusicaRepository;

    public ListaDeMusicaServices(){
        listaDeMusicaRepository = new ListaDeMusicaRepository();
    }


    public void salvarPlaylist(Playlist playlist, Musica musica) {
        try {
            listaDeMusicaRepository.adicionar(playlist, musica);
        } catch (SQLException e) {
            System.out.println("Ops! Algum erro aconteceu ao salvar a playlist");
        }

    }
}
