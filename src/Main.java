import models.Ouvinte;
import models.Playlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Main {



    public static void main(String[] args) {

//        Ouvinte ouvinte = getOuvinte();
//        Playlist playlist = getPlaylist(ouvinte);

        ArrayList<Pessoa> pessoas = new ArrayList<>();
        Pessoa p1 = new Pessoa("Cleber");
        Pessoa p2 = new Pessoa("Rafael");
        pessoas.add(p1);
        pessoas.add(p2);

        p1 = null;
        System.out.println(pessoas);



    }

    public static Ouvinte getOuvinte() {
        return new Ouvinte("Cleber", 1,
                "13-10-1994", "Ma", true);
    }

    public static Playlist getPlaylist(Ouvinte ouvinte) {
        return new Playlist("Raqs music", "01", ouvinte);
    }
}

class Pessoa {
    String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }
}
