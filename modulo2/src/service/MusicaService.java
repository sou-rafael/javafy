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

    public boolean listarMusica() {
        try {
            List<Musica> musicas = musicaRepository.listar();
            for (Musica musica: musicas) {
                System.out.println("ID: " + musica.getIdMusica() + " | Nome: " + musica.getNome());
            }
            return musicas.size() > 0;

        } catch (BancoDeDadosException e) {
            System.out.println("OPS! Algum erro aconteceu.");
            return false;
        }
    }

    public Musica getMusica(Integer id) {
        try {
            return musicaRepository.getMusica(id);
        } catch (BancoDeDadosException e){
            System.out.println("Erro ao buscar a musica. Verifique se o id foi digitado corretamente");
            return null;
        }
    }
}
