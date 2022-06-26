package utils;

import abstracts.User;
import models.Ouvinte;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class Menus {
    public static Scanner scanner = new Scanner(System.in);

    public static void  menuEscolha(){
        System.out.println("-------------------------------------------");
        System.out.println("[1] - Atualizar Perfil  [2] - Buscar Musicas");
        System.out.println("[3] - Buscar Artistas   [4] - Ver seguidores");
        System.out.println("[5] - Ver seguindo      [6] - Biblioteca    ");
        System.out.println("[7] - Suas informações  [8] - Sair          ");
        System.out.println("-------------------------------------------");
        System.out.print("Escolha: ");
    }

    public static void menuAtualizacoesPerfil(){
        System.out.println("-------------------------------------------");
        System.out.println("[1] - Editar Nome     [2] - Editar Nascimento");
        System.out.println("[3] - Editar Gênero   [4] - Editar Plano");
        System.out.println("[5] - Tudo            [6] - SAIR     ");
        System.out.println("-------------------------------------------");
        System.out.print("Escolha: ");
    }

    public static void suasInformacoes(Ouvinte ouvinte){
        System.out.println(ouvinte);
        Boolean voltarMenuPrincipal = false;
        do {
            System.out.print("Voltar para o menu principal [1] - Sim: ");
            String escolha = Menus.scanner.nextLine();
            if(escolha.equals("1")) {
                voltarMenuPrincipal = true;
                break;
            } else {
                System.out.println("OPS! Opção inválida, tente novamente.");
            }
        } while (!voltarMenuPrincipal);
    }

    public static void verSeguidores(Ouvinte ouvinte){
        for (User ovt:
             ouvinte.getSeguidores()) {
            ovt.informacoesMinimas();
        }
        Boolean voltarMenuPrincipal = false;
        do {
            System.out.print("Voltar para o menu principal [1] - Sim: ");
            String escolha = Menus.scanner.nextLine();
            if(escolha.equals("1")) {
                voltarMenuPrincipal = true;
                break;
            } else {
                System.out.println("OPS! Opção inválida, tente novamente.");
            }
        } while (!voltarMenuPrincipal);
    }


    public static void atualizarInformacoes(Ouvinte ouvinte){
        boolean voltarParaMenuPrincipal = false;
        System.out.println("AQUIIIIIIIII");
        do {
            Menus.menuAtualizacoesPerfil();
            String escolha = Menus.scanner.nextLine();

            switch (escolha){
                case "1":
                    System.out.print("Novo nome: ");
                    ouvinte.setNome(Menus.scanner.nextLine());
                    System.out.println("Editado com sucesso!!! \n");
                    break;
                case "2":
                    System.out.print("Data de nascimento: ");
                    ouvinte.setDataDeNascimento(Menus.scanner.nextLine());
                    System.out.println("Editado com sucesso!!! \n");
                    break;
                case "3":
                    System.out.print("Gênero: ");
                    ouvinte.setGenero(Menus.scanner.nextLine());
                    System.out.println("Editado com sucesso!!! \n");
                    break;
                case "4":
                    while (true){
                        System.out.print("Plano: 1 - Premium  2 - Normal: ");
                        int novoPlano = Menus.scanner.nextInt();
                        Menus.scanner.nextLine();
                        if(novoPlano < 1 || novoPlano > 2){
                            System.out.println("OPS! Opção inválida, tente novamente.");
                        } else {
                            ouvinte.setPremium(novoPlano == 1);
                            System.out.println("Editado com sucesso!!! \n");
                            break;
                        }
                    }
                    break;
                case "5":
                    break;
                case "6":
                    voltarParaMenuPrincipal = true;
                    break;
                default:
                    System.out.println("OPS! Opção inválida, tente novamente.");
                    break;
            }
        } while (!voltarParaMenuPrincipal);
    }


}
