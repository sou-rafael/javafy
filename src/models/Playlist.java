package models;

import abstracts.Conta;
import interfaces.CrudPlaylist;

import java.util.ArrayList;

public class Playlist implements CrudPlaylist {

    private String nomePlaylist;
    private ArrayList <String> listaMusicas = new ArrayList<>();
    private String id;
    private Conta propietario;

    @Override
    public boolean atualizar(String musica) {
        listaMusicas.add(musica);
        return listaMusicas.contains(musica);
    }

    @Override
    public boolean deletarPlayList() {
        return false;
    }

    @Override
    public boolean removerMusica(String musica) {
        return false;
    }

    @Override
    public void adicionar(String musica) {

    }

    @Override
    public void listar() {

    }
}
