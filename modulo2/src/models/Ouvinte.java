package models;

import abstracts.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ouvinte extends Usuario {

    private Integer idOuvinte;
    private List<Playlist> playlists = new ArrayList<>();

    public Ouvinte() {}

    public Ouvinte(Integer idUser, String nome, String dataNascimento, String genero,
                   Integer premium, Integer idOuvinte) {
        super(idUser, nome, dataNascimento, genero, premium);
        this.idOuvinte = idOuvinte;
    }

    public Integer getIdOuvinte() {
        return this.idOuvinte;
    }

    public void setIdOuvinte(Integer idOuvinte) {
        this.idOuvinte = idOuvinte;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        if(playlists != null) {
            this.playlists = playlists;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ouvinte)) return false;
        Ouvinte ouvinte = (Ouvinte) o;
        return idOuvinte.equals(ouvinte.idOuvinte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOuvinte);
    }

    @Override
    public String toString() {
        return "Ouvinte " +
                "\nNome = " + getNome()+
                "\nData de Nascimento = " + getDataNascimento()+
                "\nGenero = " + getGenero()+
                "\nUsuario premium = " +getPremium()
                ;
    }
}
