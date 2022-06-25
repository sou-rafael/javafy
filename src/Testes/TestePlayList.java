package Testes;

import models.Musica;
import models.Ouvinte;
import models.Playlist;

public class TestePlayList {

    public static void main(String[] args) {
        Ouvinte ouvinte = new Ouvinte("Cleber", 1,
                "13-10-1994", "Ma", true);

        Playlist playlist = new Playlist("Raqs music", "01", ouvinte);

        Musica msq1 = new Musica();

    }

}
