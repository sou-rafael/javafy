package models;

import abstracts.Usuario;

import java.util.Objects;

public class Ouvinte extends Usuario {

    private Integer id_ouvinte;

    public Ouvinte(Integer id_user, String nome, String dataNascimento, String genero,
                   String premium, Integer id_ouvinte) {
        super(id_user, nome, dataNascimento, genero, premium);
        this.id_ouvinte = id_ouvinte;
    }

    public Integer getId_ouvinte() {
        return id_ouvinte;
    }

    public void setId_ouvinte(Integer id_ouvinte) {
        this.id_ouvinte = id_ouvinte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ouvinte ouvinte = (Ouvinte) o;
        return Objects.equals(id_ouvinte, ouvinte.id_ouvinte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_ouvinte);
    }
}
