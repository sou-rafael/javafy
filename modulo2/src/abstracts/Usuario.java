package abstracts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public abstract class Usuario {

    private Integer id_user;
    private String nome;
    private String dataNascimento;
    private String genero;
    private String premium;
    private List<Usuario> seguidores = new ArrayList<>();
    private List<Usuario> seguindo = new ArrayList<>();

    public Usuario() {}

    public Usuario(Integer id_user, String nome, String dataNascimento, String genero, String premium) {
        this.id_user = id_user;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.premium = premium;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Usuario> seguidores) {
        if(seguidores != null) {
            this.seguidores = seguidores;
        }
    }

    public List<Usuario> getSeguindo() {
        return seguindo;
    }

    public void setSeguindo(List<Usuario> seguindo) {
        if(seguindo != null) {
            this.seguindo = seguindo;
        }
    }

}
