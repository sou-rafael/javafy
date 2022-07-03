package views;

import abstracts.Usuario;
import exceptions.BancoDeDadosException;
import models.Artista;
import models.Ouvinte;
import models.Playlist;
import repository.ConexaoBancoDeDados;
import repository.OuvinteRepositorio;
import repository.PlayListRepository;
import service.OuvinteService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static views.Menus.ouvinte;

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

    public static Ouvinte criarOuvinte () throws BancoDeDadosException {
        System.out.println("CRIAR CONTA");
        System.out.print("Nome do usuário: ");
        String nome = Menus.getString();
        System.out.print("Data de nascimento: ");
        String dataNascimento = Menus.getString();
        System.out.print("Seu genêro: ");
        String genero = Menus.getString();
        Integer isPremium = 2;
        while(true){
            System.out.print("Conta Premium: [1] - Sim [2] - Não: ");
            int tipo = Menus.getNumeric();
            if(tipo < 1 || tipo > 2){
                System.out.println("OPS! Opção inválida, tente novamente.");
            } else {
                isPremium = tipo;
                break;
            }
        }

        ouvinte = new Ouvinte(null,nome,dataNascimento,genero, isPremium,
                null);
        ouvinteService.adicionarOuvinte(ouvinte);
        return ouvinte;
    }

    public static void main(String[] args) throws BancoDeDadosException {

        int escolhaUser;

        System.out.println("================== INICIANDO APLICAÇÃO ===================");
        boolean controleIniciarAplicacao = true;
        while (controleIniciarAplicacao){
            System.out.println("[1] - CARREGAR USUÁRIO     [2] - CRIAR NOVO USUÁRIO");
            escolhaUser = Menus.getNumeric();
            switch (escolhaUser){
                case 1 -> {
                    ouvinteService.criarUsuarioRapido();
                    controleIniciarAplicacao = false;
                } case 2-> {
                    ouvinteService.criarOuvinte();
                    controleIniciarAplicacao = false;
                }
                default -> Menus.imprimirRed("Selecione uma opção válida!");
            }
        }

        System.out.println("==========================================================");

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
            //REMOVER CASE 8**********************************************************
                case 8 -> Menus.verAlbum();
                default -> System.out.println(Menus.errorSelecionar);
            }
        }

    }
}
