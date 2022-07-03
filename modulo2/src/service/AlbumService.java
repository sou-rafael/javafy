package service;

import exceptions.BancoDeDadosException;
import models.Album;
import models.Playlist;
import repository.AlbumRepository;

import java.sql.SQLException;
import java.util.List;

public class AlbumService {
    private AlbumRepository albumRepository;
    public AlbumService() {
        this.albumRepository = new AlbumRepository();
    }

    //criar
    public void adicionarAlbum(Album album){
        try {
            album = albumRepository.adicionar(album);
            System.out.println("Album criado com sucesso");
        } catch (BancoDeDadosException e) {
            System.out.println("Error ao criar playlist");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // complementos

    //delete
    public void removerAlbum(Integer id) {
        try {
            boolean conseguiuRemover = albumRepository.remover(id);
            System.out.println("Ambum com id = "+id+ " foi removido? " + conseguiuRemover);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // update
    public void editarAlbum(Integer id, Album album) {
        try {
            boolean conseguiuEditar = albumRepository.editar(id, album);
            System.out.println("Ambum com id = "+id+ " foi editado? " + conseguiuEditar);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // read
    public void listarAlbum() {
        try {
            List<Album> listar = albumRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }


}
