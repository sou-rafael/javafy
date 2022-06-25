package models;

import java.util.List;

public class Artista extends User{
    private String bio;
    private Integer avaliacao;
    private List<Album> albuns;

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
        this.albuns = albuns;
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
