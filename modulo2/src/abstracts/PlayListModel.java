package abstracts;

import models.Musica;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class PlayListModel<T> {

    private Integer id_playlist;
    private String nome;
    private T proprietario;
    private List<Musica> musicas = new ArrayList<>();

    public PlayListModel(){}

    public PlayListModel(Integer id_playlist, String nome, T proprietario) {
        this.id_playlist = id_playlist;
        this.nome = nome;
        this.proprietario = proprietario;
    }

    public Integer getId_playlist() {
        return id_playlist;
    }

    public void setId_playlist(Integer id_playlist) {
        this.id_playlist = id_playlist;
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
        if(musicas != null) {
            this.musicas = musicas;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayListModel<?> that = (PlayListModel<?>) o;
        return Objects.equals(id_playlist, that.id_playlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_playlist);
    }
}
