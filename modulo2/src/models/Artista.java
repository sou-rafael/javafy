package models;

import abstracts.Usuario;

import java.util.Objects;

public class Artista extends Usuario {

    private Integer idArtista;
    private String bio;
    private Integer avaliacao;

    public Artista() {}

    public Artista(Integer id_user, String nome, String dataNascimento, String genero,
                   Integer premium, Integer id_artista, String bio, Integer avaliacao) {
        super(id_user, nome, dataNascimento, genero, premium);
        this.idArtista = id_artista;
        this.bio = bio;
        this.avaliacao = avaliacao;
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
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
        if (!(o instanceof Artista)) return false;
        Artista artista = (Artista) o;
        return Objects.equals(idArtista, artista.idArtista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArtista);
    }

    @Override
    public String toString() {
        return "Artista{" +
                "idArtista=" + idArtista +
                ", bio='" + bio + '\'' +
                ", avaliacao=" + avaliacao +
                '}';
    }
}
