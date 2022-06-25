package models;

import abstracts.Conta;
import interfaces.CrudPlaylist;

import java.util.ArrayList;

public class Playlist implements CrudPlaylist {

    private String nomePlaylist;
    private ArrayList <Musica> listaMusicas = new ArrayList<>();
    private String id;
    private Conta propietario;

    public Playlist(){}

    public Playlist(String nomePlaylist,  String id, Conta propietario) {
        this.nomePlaylist = nomePlaylist;
        this.id = id;
        this.propietario = propietario;
    }

    @Override
    public boolean atualizarNomePlayList(String novoNome) {
        if(novoNome != null){
            nomePlaylist = novoNome;
            return true;
        }
        return false;
    }

    @Override
    public boolean deletarPlayList() {
        return true;
    }

    @Override
    public boolean removerMusica(Musica musica, Conta userPlayList ) {
        if(propietario.equals(userPlayList)){
            return listaMusicas.remove(musica);
        }
        return false;
    }

    @Override
    public void adicionar(ArrayList<Musica> musicas) {
        setListaMusicas(musicas);
    }

    @Override
    public void listar() {
        if(listaMusicas != null){
            listaMusicas.forEach(System.out::println);
        }
    }

    public String getNomePlaylist() {
        return nomePlaylist;
    }

    public void setNomePlaylist(String nomePlaylist) {
        if(nomePlaylist != null){
            this.nomePlaylist = nomePlaylist;
        }
    }

    public ArrayList<Musica> getListaMusicas() {
        return listaMusicas;
    }

    public void setListaMusicas(ArrayList<Musica> listaMusicas) {
        if(listaMusicas != null){
            this.listaMusicas = listaMusicas;
        }
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
        if(this.propietario == null && propietario != null){
            this.propietario = propietario;
        }
    }

    @Override
    public String toString() {
        return "Playlist{ nomePlaylist='" + nomePlaylist +
                ", propietario=" + propietario.getNome() +
                ", quantidade de musicas: " + getListaMusicas().size() +
                "}";

    }
}
