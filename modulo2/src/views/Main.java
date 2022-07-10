package views;

import abstracts.Usuario;
import exceptions.BancoDeDadosException;
import models.Artista;
import models.Ouvinte;
import models.Playlist;
import repository.OuvinteRepositorio;
import repository.PlayListRepository;

import java.sql.SQLException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args)  {
        int escolhaUser;
        boolean continuarNaAplicacao = true;

        while (continuarNaAplicacao) {
            Menus.menuPrincipal();
            escolhaUser = Menus.getNumeric();
            switch (escolhaUser) {
                case 0:
                    Menus.verEditarInformacoes();
                    break;
                case 1:
                    Menus.buscarMusica();
                    break;
                case 2:
                    Menus.verArtista();
                    break;
                case 3:
                    Menus.verSeguidores();
                    break;
                case 4:
                    Menus.verSeguindo();
                    break;
                case 5:
                    Menus.verBibliotecas();
                    break;
                case 6:
                    Menus.verInformacoesUser();
                    break;
                case 7:
                    System.out.println(Menus.saindoDaAplicacao);
                    continuarNaAplicacao = false;
                    break;
                default:
                    System.out.println(Menus.errorSelecionar);
                    break;
            }
        }
    }
}
