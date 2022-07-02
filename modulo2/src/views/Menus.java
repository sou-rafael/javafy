package views;

import java.util.Scanner;

public class Menus {
    // STRINGS STATICAS PARA O PROJETO
    public static Integer escolhaUser = 0;
    public static String mudancasUser = "";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String escolhaMenu = ANSI_YELLOW + "Escolha: " + ANSI_RESET;
    public static final Scanner scanner = new Scanner(System.in);
    public static final String mensagemErrorNumberFormat = ANSI_RED + "Ops!!! A escolha deve ser número. Tente novamente." + ANSI_RESET;
    public static final String errorSelecionar = ANSI_RED + "Ops!!! Opção não disponível." + ANSI_RESET;
    public static final String saindoDaAplicacao = ANSI_BLUE + "Saindo da aplicacao...." + ANSI_RESET;

    public static Integer getNumeric() {
        int escolha = 0;
        while (true) {
            try {
                System.out.print(Menus.escolhaMenu);
                escolha =  Integer.parseInt(Menus.scanner.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println(Menus.mensagemErrorNumberFormat);
            }
        }
        return escolha;
    }

    public static String getString() {
        return Menus.scanner.nextLine();
    }

    // MENU PRINCIPAL
    public static void menuPrincipal(){
        System.out.println("================== MENU PRINCIPAL ==================");
        System.out.println("[0] - ATUALIZAR PERFIL      [1] - BUSCAR MUSICA");
        System.out.println("[2] - ARTISTAS              [3] - VER SEGUIDORES");
        System.out.println("[4] - VER SEGUINDO          [5] - BIBLIOTECAS");
        System.out.println("[6] - SUAS INFORMACOES      [7] - SAIR");
        System.out.println("====================================================");

    }
    // SUBMENUS

    // Para o menu de informações do usuário - ESCOLHA 0
    public static void verEditarInformacoes() {
        System.out.println("================ ATUALIZAR USUÁRIO =================");
        System.out.println("[0] - EDITAR NOME           [1] - EDITAR NASCIMENTO");
        System.out.println("[2] - EDITAR GÊNERO         [3] - EDITAR PLANO");
        System.out.println("[5] - EDITAR TUDO           [6] - VOLTAR");
        System.out.println("====================================================");

        boolean usoValido = true;

        while (usoValido) {
            escolhaUser = Menus.getNumeric();
            switch (escolhaUser) {
                case 0:
                    System.out.println(0);
                    usoValido = false;
                    break;
                case 1:
                    System.out.println(1);
                    usoValido = false;
                    break;
                case 2:
                    System.out.println(2);
                    usoValido = false;
                    break;
                case 3:
                    System.out.println(3);
                    usoValido = false;
                    break;
                case 4:
                    System.out.println(4);
                    usoValido = false;
                    break;
                case 5:
                    System.out.println(5);
                    usoValido = false;
                    break;
                case 6:
                    System.out.println(6);
                    usoValido = false;
                    break;
                default:
                    System.out.println(errorSelecionar);
                    break;
            }
        }
    }

    // MENU EXPLORAR MUSICA - ESCOLHA 1
    // Para o menu de informações do usuário
    public static void buscarMusica() {
        System.out.println("================ EXPLORE MUSICAS ==================");
        System.out.println("[0] - INFORMAÇÃO - MUSICA   [1] - BUSCAR MUSICA");
        System.out.println("[2] - TOP 5 MUSICAS         [3] - VOLTAR");
        System.out.println("====================================================");
        escolhaUser = Menus.getNumeric();
    }

    // MENU EXPLORAR MUSICA - ESCOLHA 2
    public static void verArtista() {
        System.out.println("================ EXPLORAR ARTISTAS ==================");
        System.out.println("[0] - INFORMAÇÕES DO ARTISTA    [1] - BUSCAR ALBUNS");
        System.out.println("[2] - TOP 5 ARTISTAS            [3] - VOLTAR");
        System.out.println("=====================================================");
        escolhaUser = Menus.getNumeric();
    }

    // MENU SEGUIDORES - ESCOLHA 3
    public static void verSeguidores() {
        System.out.println("================== SEGUIDORES ======================");
        System.out.println("[0] - INFORMAÇÕES     [1] - VER PLAYLISTS/ALBUNS");
        System.out.println("[2] - VOLTAR");
        System.out.println("====================================================");
        escolhaUser = Menus.getNumeric();
    }

    // MENU SEGUINDO - ESCOLHA 4
    public static void verSeguindo() {
        System.out.println("==================== SEGUINDO ======================");
        System.out.println("[0] - INFORMAÇÕES    [1] - VER PLAYLISTS/ALBUNS");
        System.out.println("[2] - VOLTAR");
        System.out.println("====================================================");
        escolhaUser = Menus.getNumeric();
    }

    // MENU BIBLIOTECA - ESCOLHA 5
    public static void verBibliotecas() {
        System.out.println("==================== BIBLIOTECA ===================");
        System.out.println("[0] - VER SUAS PLAYLISTS     [1] - CRIAR PLAYLIST");
        System.out.println("[2] - VOLTAR");
        System.out.println("===================================================");
        escolhaUser = Menus.getNumeric();
    }

    // MENU INFORMAÇÕES USUÁRIO - ESCOLHA 6
    public static void verInformacoesUser() {
        System.out.println("=============== PERFIL DO USUÁRIO =================");
        // mostrar informações
        System.out.println("===================================================");
        escolhaUser = Menus.getNumeric();
    }

}
