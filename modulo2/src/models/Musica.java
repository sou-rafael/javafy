package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Musica {
    private Integer id_musica;
    private Album album;
    private String nome;
    private Integer avaliacao;
    private Double duracao;
    private Integer curtidas;
    
    public Musica() {}

    public Musica(Integer id_musica, Album album, String nome, Integer avaliacao,
                  Double duracao, Integer curtidas) {
        this.id_musica = id_musica;
        this.album = album;
        this.nome = nome;
        this.avaliacao = avaliacao;
        this.duracao = duracao;
        this.curtidas = curtidas;
    }

    public Integer getId_musica() {
        return id_musica;
    }

    public void setId_musica(Integer id_musica) {
        this.id_musica = id_musica;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Double getDuracao() {
        return duracao;
    }

    public void setDuracao(Double duracao) {
        this.duracao = duracao;
    }

    public Integer getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(Integer curtidas) {
        this.curtidas = curtidas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Musica musica = (Musica) o;
        return Objects.equals(id_musica, musica.id_musica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_musica);
    }
}
