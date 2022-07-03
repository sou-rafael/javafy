package service;

import exceptions.BancoDeDadosException;
import models.Artista;
import repository.ArtistaRepository;

import java.util.List;

public class ArtistaService {
    private ArtistaRepository artistaRepository;
    public ArtistaService(){
        this.artistaRepository = new ArtistaRepository();
    }
    public void listarArtistas() {
        try {
            List<Artista> listar = artistaRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
