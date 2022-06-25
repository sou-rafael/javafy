package models;

import java.util.Objects;

public class Musica {

    private String id;
    Artista artista;
    private Integer avaliacao;
    private Double duracao;
    private Integer curtidas;

    public Musica() {}

    public Musica(String id, Artista artista, Integer avaliacao, Double duracao, Integer curtidas) {
        this.id = id;
        this.artista = artista;
        this.avaliacao = avaliacao;
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

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        if(avaliacao != null){
            this.avaliacao = avaliacao;
        }
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
        return "Musica{id='" + id +
                ", avaliacao=" + avaliacao +
                ", duracao=" + duracao +
                ", curtidas=" + curtidas +
                "}";
    }
}
