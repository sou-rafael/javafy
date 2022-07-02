package abstracts;

import models.Musica;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class PlayListModel<T> {

    private Integer idPlaylist;
    private String nome;
    private T proprietario;
    private List<Musica> musicas = new ArrayList<>();

    public PlayListModel(){}

    public PlayListModel(Integer idPlaylist, String nome, T proprietario) {
        this.idPlaylist = idPlaylist;
        this.nome = nome;
        this.proprietario = proprietario;
    }

    public Integer getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(Integer idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public T getProprietario() {
        return proprietario;
    }

    public void setProprietario(T proprietario) {
        this.proprietario = proprietario;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return "PlayListModel{" +
                "idPlaylist=" + idPlaylist +
                ", nome='" + nome + '\'' +
                ", proprietario=" + proprietario +
                ", musicas=" + musicas +
                '}';
    }
}
