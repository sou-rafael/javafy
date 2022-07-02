package models;

import abstracts.User;

import java.util.ArrayList;
import java.util.List;

public class Artista extends User {

    private String bio;
    private Double avaliacao;
    private ArrayList<Album> albuns;


    public Artista(String nome, String id, String dataDeNascimento, String genero,
                   boolean premium, String bio, Double avaliacao) {
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

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(ArrayList<Album> albuns) {
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
