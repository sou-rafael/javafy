package models;

import interfaces.Informacoes;

import java.util.Objects;

public class Musica {

    private String id;
    private String nomeDaMusica;
    Artista artista;
    private Double duracao;
    private Integer curtidas;

    public Musica() {}


    public Musica(String id, String nomeDaMusica, Artista artista, Double duracao, Integer curtidas) {
        this.id = id;
        this.nomeDaMusica = nomeDaMusica;
        this.artista = artista;
        this.duracao = duracao;
        this.curtidas = curtidas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id != null){
            this.id = id;
        }
    }

    public String getNomeDaMusica() {
        return nomeDaMusica;
    }

    public void setNomeDaMusica(String nomeDaMusica) {
        this.nomeDaMusica = nomeDaMusica;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Double getDuracao() {
        return duracao;
    }

    public void setDuracao(Double duracao) {
        if(duracao != null){
            this.duracao = duracao;
        }
    }

    public Integer getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(Integer curtidas) {
        if(curtidas != null) {
            this.curtidas = curtidas;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Musica musica = (Musica) o;
        return id.equals(musica.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{ Nome da musica: " + getNomeDaMusica() +
                ", Artista: " + getArtista().getNome() +
                ", Curtidas: " + getCurtidas()+" } ";
    }
}
