package views;

import exceptions.BancoDeDadosException;
import models.Ouvinte;
import repository.ConexaoBancoDeDados;
import service.OuvinteService;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static OuvinteService ouvinteService = new OuvinteService();

    static Connection con;

    static {
        try {
            con = ConexaoBancoDeDados.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ;

    public static void main(String[] args) throws BancoDeDadosException {
        // Criar o usuário
        ouvinteService.criarOuvinte();

        int escolhaUser;
        boolean continuarNaAplicacao = true;

        while (continuarNaAplicacao) {
            Menus.menuPrincipal();
            escolhaUser = Menus.getNumeric();
            switch (escolhaUser) {
                case 0 -> Menus.verEditarInformacoes();
                case 1 -> Menus.buscarMusica();
                case 2 -> Menus.verArtista();
                case 3 -> Menus.verSeguidores();
                case 4 -> Menus.verSeguindo();
                case 5 -> Menus.verBibliotecas();
                case 6 -> Menus.verInformacoesUser();
                case 7 -> {
                    System.out.println(Menus.saindoDaAplicacao);
                    continuarNaAplicacao = false;
                }
                default -> System.out.println(Menus.errorSelecionar);
            }
        }
    }
}
