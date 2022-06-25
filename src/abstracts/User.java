package abstracts;

import abstracts.Conta;

import java.util.ArrayList;
import java.util.List;

public abstract class User extends Conta {

    private List<Conta> seguidores = new ArrayList<>();
    private List<Conta> seguindo = new ArrayList<>();
    private boolean premium;

    public User(){}

    public User(String nome, Integer id, String dataDeNascimento,
                String genero, boolean premium) {
        super(nome, id, dataDeNascimento, genero);
        this.premium = premium;
    }

    public List<Conta> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Conta> seguidores) {
        if(seguidores != null){
            this.seguidores = seguidores;
        }
    }

    public List<Conta> getSeguindo() {
        return seguindo;
    }

    public void setSeguindo(List<Conta> seguindo) {
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
