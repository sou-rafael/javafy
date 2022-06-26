import models.Ouvinte;
import models.Playlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Main {



    public static void main(String[] args) {




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
