package abstracts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public abstract class Usuario {

    private Integer idUser;
    private String nome;
    private String dataNascimento;
    private String genero;
    private Integer premium;
    private List<Usuario> seguidores = new ArrayList<>();
    private List<Usuario> seguindo = new ArrayList<>();

    public Usuario() {}

    public Usuario(Integer idUser, String nome, String dataNascimento, String genero, Integer premium) {
        this.idUser = idUser;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.premium = premium;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public Integer getPremium() {
        return premium;
    }

    public void setPremium(Integer premium) {
        this.premium = premium;
    }

    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Usuario> seguidores) {
        this.seguidores = seguidores;
    }

    public List<Usuario> getSeguindo() {
        return seguindo;
    }

    public void setSeguindo(List<Usuario> seguindo) {
        this.seguindo = seguindo;
    }

    public void imprimirInformacoesPersonalizadas(){
        String leftAlignFormat = "| %-7s | %-25s | %-15s %n";
        System.out.format(leftAlignFormat, "Id: " + getIdUser(), "Nome: "
                + getNome(), "Genero: "+ getGenero() );

    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUser=" + idUser +
                ", nome='" + nome + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", genero='" + genero + '\'' +
                ", premium='" + premium + '\'' +
                ", seguidores=" + seguidores +
                ", seguindo=" + seguindo +
                '}';
    }
}
