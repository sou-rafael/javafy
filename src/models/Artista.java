package models;

import interfaces.Admin;

import java.util.List;

public class Artista extends User implements Admin {

    private String bio;
    private Integer avaliacao;
    private List<Album> albuns;

    public Artista(String nome, Integer id, String dataDeNascimento, String genero,
                   boolean premium, String bio, Integer avaliacao) {
        super(nome, id, dataDeNascimento, genero, premium);
        this.bio = bio;
        this.avaliacao = avaliacao;
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

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        if(albuns != null){
            this.albuns = albuns;
        }
    }

    @Override
    public String toString() {
        return "Artista{" +
                "bio='" + bio + '\'' +
                ", avaliacao=" + avaliacao +
                ", albuns=" + albuns +
                '}';
    }
}
