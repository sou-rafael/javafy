package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Musica {
    private Integer idMusica;
    private Album album;
    private String nome;
    private Integer avaliacao;
    private Double duracao;
    private Integer curtidas;
    
    public Musica() {}

    public Musica(Integer id_musica, Album album, String nome, Integer avaliacao,
                  Double duracao, Integer curtidas) {
        this.idMusica = id_musica;
        this.album = album;
        this.nome = nome;
        this.avaliacao = avaliacao;
        this.duracao = duracao;
        this.curtidas = curtidas;
    }

    public Integer getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(Integer idMusica) {
        this.idMusica = idMusica;
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

    public void imprimirMusica(){
        String leftAlignFormat = "| %-5s | %-30s | %-15s %n";
        System.out.format(leftAlignFormat, "Id: " + getIdMusica(), "Musica: "
                + getNome(), "Curtidas: "+ getCurtidas() );
        /*System.out.println("| Id Musica: " + getIdMusica() + " | Nome da musica: "
                + getNome() + " | Curtidas: "+ getCurtidas() + " | Avaliacao: " + getAvaliacao());*/
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Musica musica)) return false;
        return idMusica.equals(musica.idMusica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMusica);
    }

    @Override
    public String toString() {
        return "Musica{" +
                "idMusica=" + idMusica +
                ", album=" + album +
                ", nome='" + nome + '\'' +
                ", avaliacao=" + avaliacao +
                ", duracao=" + duracao +
                ", curtidas=" + curtidas +
                '}';
    }
}
