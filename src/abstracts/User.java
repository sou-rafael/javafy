package abstracts;


import java.util.ArrayList;
import java.util.List;

public abstract class User {

    private String nome;
    private Integer id;
    private String dataDeNascimento;
    private String genero;

    private ArrayList<User> seguidores = new ArrayList<>();
    private ArrayList<User> seguindo = new ArrayList<>();
    private boolean premium;

    public User(String nome, Integer id, String dataDeNascimento, String genero, boolean premium) {
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
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    // colocar um if para colocar "premium ou gratuito" no lugar de booleano de premium
    @Override
    public String toString() {
        if (premium) {
            return "User{" +
                    "seguidores=" + seguidores +
                    ", seguindo=" + seguindo +
                    ", tipo de usuario= Premium" + '}';
        } else {
            return "User{" +
                    "seguidores=" + seguidores +
                    ", seguindo=" + seguindo +
                    ", tipo de usuario= Gratuito" +
                    '}';
        }
    }
}
