package models;

import abstracts.User;
import interfaces.CrudPlayList;

import java.util.ArrayList;
import java.util.Objects;

public class Playlist implements CrudPlayList {

    private String nomePlaylist;
    private ArrayList <Musica> listaMusicas = new ArrayList<>();
    private String id;
    private User propietario;

    public Playlist(){}

    public Playlist(String nomePlaylist,  String id, User propietario) {
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
    public boolean removerMusica(Musica musica, User userPlayList ) {
        if(propietario.getId().equals(userPlayList.getId())){
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

    public User getPropietario() {
        return propietario;
    }

    public void setPropietario(User propietario) {
        if(this.propietario == null && propietario != null){
            this.propietario = propietario;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(nomePlaylist, playlist.nomePlaylist) && Objects.equals(id, playlist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomePlaylist, id);
    }

    @Override
    public String toString() {
        return "Playlist{ nomePlaylist='" + nomePlaylist +
                ", propietario=" + propietario.getNome() +
                ", quantidade de musicas: " + getListaMusicas().size() +
                "}";

    }
}
