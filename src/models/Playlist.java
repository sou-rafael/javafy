package models;

import abstracts.Conta;
import interfaces.CrudPlaylist;

import java.util.ArrayList;

public class Playlist implements CrudPlaylist {

    private String nomePlaylist;
    private ArrayList <Musica> listaMusicas = new ArrayList<>();
    private String id;
    private Conta propietario;

    @Override
    public boolean atualizar(Musica musica) {
        listaMusicas.add(musica);
        return listaMusicas.contains(musica);
    }

    @Override
    public boolean deletarPlayList() {

        listaMusicas.clear();
        return listaMusicas.isEmpty();
    }

    @Override
    public boolean removerMusica(Musica musica) {

        return listaMusicas.remove(musica);
    }

    @Override
    public void adicionar(Musica musica) {

        listaMusicas.add(musica);

    }

    @Override
    public void listar() {

        if(listaMusicas != null){
            listaMusicas.stream()
                    .forEach(System.out::println);
        }

    }

    public Playlist(String nomePlaylist, ArrayList<Musica> listaMusicas, String id, Conta propietario) {
        this.nomePlaylist = nomePlaylist;
        this.listaMusicas = listaMusicas;
        this.id = id;
        this.propietario = propietario;
    }

    public String getNomePlaylist() {
        return nomePlaylist;
    }

    public void setNomePlaylist(String nomePlaylist) {
        this.nomePlaylist = nomePlaylist;
    }

    public ArrayList<Musica> getListaMusicas() {
        return listaMusicas;
    }

    public void setListaMusicas(ArrayList<Musica> listaMusicas) {
        this.listaMusicas = listaMusicas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Conta getPropietario() {
        return propietario;
    }

    public void setPropietario(Conta propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "nomePlaylist='" + nomePlaylist + '\'' +
                ", id='" + id + '\'' +
                ", propietario=" + propietario.getNome() +
                '}';

    }
}
