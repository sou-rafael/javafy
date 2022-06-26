package Testes;

import models.Artista;
import models.Ouvinte;
import models.Playlist;
import utils.Menus;

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class UsuariosTeste {

    public static Ouvinte createOuvinte(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("CRIAR CONTA");
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Data de nascimento: ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Seu genêro: ");
        String genero = scanner.nextLine();

        boolean isPremium = false;
        while(true){
            System.out.print("Conta Premium: [1] - Sim [2] - Não: ");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            if(tipo < 1 || tipo > 2){
                System.out.println("OPS! Opção inválida, tente novamente.");
            } else {
                isPremium = tipo == 1;
                break;
            }
        }
        UUID uuid = UUID.randomUUID();
        return new Ouvinte(nome, uuid.toString(), dataNascimento, genero, isPremium);
    }



    public static void main(String[] args) throws IOException {
        //Ouvinte ouvinte = createOuvinte();
        UUID uuid = UUID.randomUUID();
        Ouvinte ouvinte = new Ouvinte("Cleber", uuid.toString(),
                "13101994", "Ma", true );

        Ouvinte ouvinte2 = new Ouvinte("Cleber", uuid.toString(),
                "13101994", "Ma", true );

        ouvinte2.seguirUser(ouvinte);

        boolean finalizarAplicacao = false;
        do{
            Menus.menuEscolha();
            String escolha = Menus.scanner.nextLine();

            switch (escolha) {
                case "1":
                    Menus.atualizarInformacoes(ouvinte);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    Menus.verSeguidores(ouvinte);
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    Menus.suasInformacoes(ouvinte);
                    break;
                case "8":
                    System.out.println("Saindo da aplicação....");
                    finalizarAplicacao = true;
                    break;
                default:
                    System.out.println("Ops! Entrada inválida");
            }

        }while (!finalizarAplicacao);


    }
}
