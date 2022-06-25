package models;

import abstracts.Conta;

import java.util.List;

public class User extends Conta {
    private List<Conta> seguidores;
    private List<Conta> seguindo;
    private boolean premium;

    public List<Conta> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Conta> seguidores) {
        this.seguidores = seguidores;
    }

    public List<Conta> getSeguindo() {
        return seguindo;
    }

    public void setSeguindo(List<Conta> seguindo) {
        this.seguindo = seguindo;
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
        if (this.premium == true) {
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
