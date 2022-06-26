package abstracts;


import interfaces.Informacoes;
import models.Musica;
import models.Playlist;

import java.util.ArrayList;
import java.util.List;

public abstract class User implements Informacoes {

    private String nome;
    private String id;
    private String dataDeNascimento;
    private String genero;

    private ArrayList<User> seguidores = new ArrayList<>();
    private ArrayList<User> seguindo = new ArrayList<>();
    private boolean premium;

    public User(String nome, String id, String dataDeNascimento, String genero, boolean premium) {
        this.nome = nome;
        this.id = id;
        this.dataDeNascimento = dataDeNascimento;
        this.genero = genero;
        this.premium = premium;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome != null) {
            this.nome = nome;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id != null) {
            this.id = id;
        }
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        if(dataDeNascimento != null) {
            this.dataDeNascimento = dataDeNascimento;
        }
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if(genero != null) {
            this.genero = genero;
        }
    }

    public ArrayList<User> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(ArrayList<User> seguidores) {
        if(seguidores != null){
            this.seguidores = seguidores;
        }
    }

    public ArrayList<User> getSeguindo() {
        return seguindo;
    }

    public void setSeguindo(ArrayList<User> seguindo) {
        if(seguindo != null){
            this.seguindo = seguindo;
        }
    }

    public void seguirUser(User user){
        if(user != null){
            this.seguindo.add(user);
            user.getSeguidores().add(this);
        }

    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public Integer getQuantidadeSeguidores(){return getSeguidores().size();}

    public Integer getQuantidadeSeguindo(){return getSeguindo().size();}

    public void informacoesMinimas(){
        System.out.println("Nome: " + getNome() + ", Genêro: " + getGenero() +
                ", Qtd seguidores: " + getQuantidadeSeguidores() +
                ", Qtd seguindo: " + getQuantidadeSeguindo());
    }

    @Override
    public String toString() {
        String ehPremiun = isPremium() ? "Premium" : "Normal";
        return "-----------------Informações do ouvinte---------------\n" +
                "Nome: " + getNome() + "\nGenêro: " + getGenero() +
                "\nPlano: " + ehPremiun + "\nData Nascimento: " + getDataDeNascimento() +
                "\nQtd seguidores: " + getQuantidadeSeguidores()
                + "\nQtd seguindo: " + getQuantidadeSeguindo() +
                "\n-----------------------------------------------------";
    }
}
