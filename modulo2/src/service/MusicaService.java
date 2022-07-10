package service;

import exceptions.BancoDeDadosException;
import models.Musica;
import repository.MusicaRepository;
import views.Menus;

import java.util.ArrayList;
import java.util.List;

public class MusicaService {
    private final MusicaRepository musicaRepository;

    public MusicaService() {
        musicaRepository = new MusicaRepository();
    }

    void adicionarMusica(){

    }

    // Retorna uma lista de musica
    public List<Musica> lista(){
        try {
            return musicaRepository.listar();
        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("OPS! Algum erro aconteceu.");
            return new ArrayList<>();
        }
    }

    // Pega uma lista de musica / retorna e imprimir
    public List<Musica> listarMusica() {
        List<Musica> musicas = lista();
        for (Musica musica: musicas) {
            musica.imprimirMusica();
        }
        return musicas;
    }

    // Busca uma musica por id
    public Musica getMusica(Integer id) {
        try {
            return musicaRepository.getMusica(id);
        } catch (BancoDeDadosException e){
            Menus.imprimirRed("Erro ao buscar a musica. Verifique se o id foi digitado corretamente");
            return null;
        }
    }

    public void filtrarMusica(String nomeDaMusica){
        try {
            List<Musica> musicas = musicaRepository.getMusicaFiltro(nomeDaMusica);
            musicas.forEach(Musica::imprimirMusica);

        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("Algum erro aconteceu");
        }
    }


}
