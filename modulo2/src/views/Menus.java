package views;

import exceptions.BancoDeDadosException;
import models.Musica;
import models.Ouvinte;
import models.Playlist;
import service.ListaDeMusicaServices;
import service.MusicaService;
import service.OuvinteService;
import service.PlayListService;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menus {
    public static MusicaService musicaService= new MusicaService();
    public static PlayListService playListService= new PlayListService();
    public static ListaDeMusicaServices listaDeMusicaServices = new ListaDeMusicaServices();

    public static OuvinteService ouvinteService = new OuvinteService();

    //formatador apenas de exemplo adequar o formatador ao banco de dados
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static Ouvinte ouvinte = new Ouvinte();

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
                case 0 -> {
                    System.out.println(0);
                    System.out.println("================ EDITAR NOME ==================");
                    System.out.println("Digite o nome do ouvinte ");
                    String novoNome = getString();
                    ouvinte.setNome(novoNome);
                    ouvinteService.editarOuvinte(ouvinte.getIdOuvinte(), ouvinte);
                    usoValido = false;
                }
                case 1 -> {
                    System.out.println(1);
                    System.out.println("================ EDITAR GENERO ==================");
                    System.out.println("Digite o genero do ouvinte ");
                    String novoGenero = getString();
                    ouvinte.setGenero(novoGenero);
                    ouvinteService.editarOuvinte(ouvinte.getIdOuvinte(), ouvinte);
                    usoValido = false;
                }
                case 2 -> {
                    System.out.println(2);
                    System.out.println("================ EDITAR DATA DE NASCIMENTO ==================");
                    System.out.println("Digite o id do ouvinte ");
                    System.out.println("Digite a data de nascimento do ouvinte ");
                    String novaIdade = getString();
                    System.out.println(ouvinte.getDataNascimento());
                    ouvinte.setDataNascimento(novaIdade);
                    System.out.println(ouvinte.getDataNascimento());
                    ouvinteService.editarOuvinte(ouvinte.getIdOuvinte(), ouvinte);
                    usoValido = false;
                }
                case 3 -> {
                    System.out.println(3);
                    System.out.println("================ EDITAR PLANO ==================");
                    System.out.println("Digite o id do ouvinte ");
                    System.out.println("Digite [1] para premium " +
                            "\nDigite qualquer outra coisa para não mudar");
                    Integer novoPlano = getNumeric();
                    if (novoPlano == 1) {
                        ouvinte.setPremium(1);
                        ouvinteService.editarOuvinte(ouvinte.getIdOuvinte(), ouvinte);
                    }
                    usoValido = false;
                }
                case 4 -> {
                    System.out.println(4);
                    usoValido = false;
                }
                case 5 -> {
                    System.out.println(5);
                    System.out.println("================ EDITAR OUVINTE ==================");
                    System.out.println("Digite o nome do ouvinte ");
                    String novoNomeTodos = getString();
                    ouvinte.setNome(novoNomeTodos);
                    System.out.println("Digite a data de nascimento (dd/MM/yyyy)");
                    String novaData = getString();
                    ouvinte.setDataNascimento(String.valueOf(LocalDate.parse(novaData, formatter)));
                    System.out.println("Digite o genero : ");
                    String novoGenero2 = getString();
                    ouvinte.setGenero(novoGenero2);
                    System.out.println("Digite [1] para premium " +
                            "\nDigite qualquer outra coisa para não mudar");
                    Integer novoPlano2 = getNumeric();
                    if (novoPlano2 == 1) {
                        ouvinte.setPremium(1);
                    }
                    ouvinteService.editarOuvinte(ouvinte.getIdOuvinte(), ouvinte);
                    usoValido = false;
                }
                case 6 -> {
                    System.out.println(6);
                    usoValido = false;
                }
                default -> System.out.println(errorSelecionar);
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

        boolean usoValido = true;

        // ESSE OUVINTE SERÁ OBTIDO POR MÉTODO
        Ouvinte ouvinte = new Ouvinte();
        ouvinte.setIdOuvinte(1);

        while (usoValido) {
            escolhaUser = Menus.getNumeric();
            switch (escolhaUser) {
                case 0 -> {
                    System.out.println("============== PLAYLISTS DO USUARIO ===============");
                    List<Playlist> playlists = playListService.ListarPlayList(ouvinte);
                    if(!playlists.isEmpty()){
                        System.out.println("[0] - SELECIONAR PLAYLIST     [1] - SAIR");
                        escolhaUser = getNumeric();

                        if(escolhaUser == 0) {
                            System.out.print("Digite o id da playlist. ");
                            escolhaUser = Menus.getNumeric();
                            Playlist playlist = listaDeMusicaServices.getMusicasWithPlayList(escolhaUser, ouvinte);
                            if(playlist != null) {
                                if(!playlist.getMusicas().isEmpty()){
                                    for (Musica musica: playlist.getMusicas()) {
                                        System.out.println("| Id Musica: " + musica.getIdMusica()
                                                + " | Nome da musica: " + musica.getNome() + " | Curtidas: "
                                                + musica.getCurtidas() + " | Avaliacao: " + musica.getAvaliacao());
                                    }
                                    System.out.println("[0] - EDITAR NOME PLAYLIST      [1] - DELETAR MUSICA DA PLAYLIST");
                                    System.out.println("[1] - SAIR");
                                    escolhaUser = Menus.getNumeric();
                                    switch (escolhaUser) {
                                        case 0 -> System.out.println("EDITAR PLAYLIST");
                                        case 1 -> {
                                            System.out.print("Digite o id da musica: ");
                                            escolhaUser = Menus.getNumeric();
                                            listaDeMusicaServices.deletarMusicaDaPlayList(playlist, ouvinte, escolhaUser);
                                        }
                                        default -> usoValido = false;
                                    }
                                } else {
                                    System.out.println("Playlist VAZIA!!!");
                                }
                            }else {
                                System.out.println("OPS! Algum erroa aconteceu!");
                            }

                        }
                        usoValido = false;
                    }
                }
                case 1 -> {
                    System.out.println("================= CRIAR PLAYLIST ==================");
                    System.out.print("Nome para playlist: ");
                    String nomePlaylist = getString();

                    // CRIRA UMA NOVA PLAYLIST
                    Playlist playlist = new Playlist();
                    playlist.setNome(nomePlaylist);
                    playlist.setProprietario(ouvinte);
                    playListService.adicionarPlaylist(playlist);

                    // CHAMAR TELA PARA ADICIONAR MUSICA NA PLAYLIST SÓ SE PLAYLIST FOR CRIADA
                    if (playlist.getIdPlaylist() != null) {
                        addMusicasNaPlayList(playlist);
                    }
                    System.out.println("===================================================");
                    usoValido = false;
                }
                case 2 -> usoValido = false;
                default -> System.out.println("Opção inválida!!! ");
            }
        }

    }
    // Método para adicionar musicas na playlist
    public static void addMusicasNaPlayList(Playlist playlist){
        boolean addMusica = true;
        while (addMusica){
            System.out.println("=========== ADD MUSICA NA PLAYLIST ==============");
            System.out.println("[0] - ADICIONAR MUSICA     [1] - SAIR");
            escolhaUser = getNumeric();

            if(escolhaUser == 0) {
                // Pegar uma lista de musicas do banco de dados
                List<Musica> musicas = musicaService.listarMusica();

                if (musicas == null || musicas.isEmpty()) {
                    continue;
                }

                System.out.print("Digite o id da musica. ");
                escolhaUser = getNumeric();
                listaDeMusicaServices.adicionarMusicaNaPlayList(playlist, escolhaUser,
                        musicaService);
            } else {
                addMusica = false;
            }
        }
    }

    public static void editarPlayListNome() {

    }

    // MENU INFORMAÇÕES USUÁRIO - ESCOLHA 6
    public static void verInformacoesUser() {
        System.out.println("=============== PERFIL DO USUÁRIO =================");
        ouvinteService.consultarOuvinte(ouvinte.getIdOuvinte());
        System.out.println("===================================================");
        escolhaUser = Menus.getNumeric();
    }

}
