package models;

import abstracts.Usuario;

import java.util.Objects;

public class Artista extends Usuario {

    private Integer id_artista;
    private String bio;
    private Integer avaliacao;

    public Artista() {}

    public Artista(Integer id_user, String nome, String dataNascimento, String genero,
                   String premium, Integer id_artista, String bio, Integer avaliacao) {
        super(id_user, nome, dataNascimento, genero, premium);
        this.id_artista = id_artista;
        this.bio = bio;
        this.avaliacao = avaliacao;
    }

    public Integer getId_artista() {
        return id_artista;
    }

    public void setId_artista(Integer id_artista) {
        this.id_artista = id_artista;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artista artista = (Artista) o;
        return Objects.equals(id_artista, artista.id_artista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_artista);
    }
}
