package models;

import abstracts.Usuario;

import java.util.Objects;

public class Ouvinte extends Usuario {

    private Integer idOuvinte;

    public Ouvinte(Integer id_user, String nome, String dataNascimento, String genero,
                   String premium, Integer idOuvinte) {
        super(id_user, nome, dataNascimento, genero, premium);
        this.idOuvinte = idOuvinte;
    }

    public Integer getIdOuvinte() {
        return idOuvinte;
    }

    public void setIdOuvinte(Integer idOuvinte) {
        this.idOuvinte = idOuvinte;
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
        return "Ouvinte{" +
                "idOuvinte=" + idOuvinte +
                '}';
    }
}
