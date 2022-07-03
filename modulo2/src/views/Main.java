package views;

import exceptions.BancoDeDadosException;
import models.Ouvinte;
import service.OuvinteService;


public class Main {

    public static OuvinteService ouvinteService = new OuvinteService();
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
