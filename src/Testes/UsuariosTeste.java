package Testes;

import models.Ouvinte;
import models.Playlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UsuariosTeste {

    public static void main(String[] args) {

        Ouvinte ouvinte = new Ouvinte("Cleber", 1,
                "13-10-1994", "Ma", true);

        Playlist playlist = new Playlist("Raqs music", 1, ouvinte);
        ouvinte.criarPlayList(playlist);

        Ouvinte ouvinte2 = new Ouvinte("JULIANA", 2,
                "13-10-1997", "Ma", true);

        boolean teste;
        // Outro usuário não pode criarPlaysList que não é ddele
        ouvinte2.criarPlayList(playlist); // não pode
        teste = ouvinte2.removerPlayList(playlist);
        System.out.println("Usuário 2 removeu playlist do usuário 1: " + teste);

        // Outro usuário - 2 tentará apagar uma playlist que não é sua
        teste = ouvinte2.deletarPlaylist(playlist);
        System.out.println("Usuário 2 deletou playlist do usuário 1: " + teste);

        teste = ouvinte.deletarPlaylist(playlist);
        System.out.println("O usuário deletou sua playlist: " + teste);
        System.out.println(ouvinte.getPlaylists());

    }
}
