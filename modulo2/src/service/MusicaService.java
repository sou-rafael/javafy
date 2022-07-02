package service;

import exceptions.BancoDeDadosException;
import models.Musica;
import repository.MusicaRepository;

import java.util.List;

public class MusicaService {
    private final MusicaRepository musicaRepository;

    public MusicaService() {
        musicaRepository = new MusicaRepository();
    }

    void adicionarMusica(){

    }

    public List<Musica> listarMusica() {
        try {
            List<Musica> musicas = musicaRepository.listar();
            return musicas;
        } catch (BancoDeDadosException e) {
            System.out.println("OPS! Algum erro aconteceu.");
            return null;
        }
    }
}
