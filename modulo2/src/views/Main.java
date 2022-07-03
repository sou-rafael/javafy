package views;

import exceptions.BancoDeDadosException;
import models.Ouvinte;
import repository.ConexaoBancoDeDados;
import service.OuvinteService;

import java.sql.SQLException;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static views.Menus.ouvinte;

public class Main {


    public static void main(String[] args)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //18/10/2020
        int escolhaUser;

        System.out.println("================== INICIANDO APLICAÇÃO ===================");
        boolean controleIniciarAplicacao = true;
        while (controleIniciarAplicacao){
            System.out.println("[1] - CARREGAR USUÁRIO     [2] - CRIAR NOVO USUÁRIO");
            escolhaUser = Menus.getNumeric();
            switch (escolhaUser){
                case 1 -> {
                    Menus.ouvinte = new Ouvinte(1, "Cleber", LocalDate.parse("13/10/1994", formatter) , "M", 1, 1 );
                    //ouvinteService.criarUsuarioRapido();
                    controleIniciarAplicacao = false;
                } case 2-> {
                    Menus.ouvinteService.criarOuvinte();
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
