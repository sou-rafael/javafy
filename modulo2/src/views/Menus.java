package views;

import exceptions.BancoDeDadosException;
import models.Album;
import models.Musica;
import models.Ouvinte;
import models.Playlist;
import service.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public static Integer getNumeric(String texto) {
        texto = ANSI_YELLOW + "" + texto + "" + ANSI_RESET;
        int escolha = 0;
        while (true) {
            try {
                System.out.print(texto);
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

    public static String getString(String texto ) {
        System.out.print(ANSI_YELLOW + "" + texto + "" + ANSI_RESET);
        return Menus.scanner.nextLine();
    }

    public static void imprimirBlue(String texto) {
        System.out.println(ANSI_BLUE + texto + ANSI_RESET);
    }

    public static void imprimirRed(String texto) {
        System.out.println(ANSI_RED + texto + ANSI_RESET);
    }

    public static void imprimirYellow(String texto) {
        System.out.println(ANSI_YELLOW + texto + ANSI_RESET);
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
                    System.out.println("================ EDITAR NOME ==================");
                    String novoNome = getString("Novo nome: ");
                    ouvinte.setNome(novoNome);
                    ouvinteService.editarOuvinte(ouvinte);
                    usoValido = false;
                }
                case 1 -> {
                    System.out.println("================ EDITAR DATA DE NASCIMENTO ==================");
                    String novaIdade = getString("Nova data de nascimento: ");
                    ouvinte.setDataNascimento(novaIdade);
                    ouvinteService.editarOuvinte(ouvinte);
                    usoValido = false;
                }
                case 2 -> {
                    System.out.println("================ EDITAR GENERO ==================");
                    String novoGenero = getString("Novo Gênero: ");
                    ouvinte.setGenero(novoGenero);
                    ouvinteService.editarOuvinte(ouvinte);
                    usoValido = false;
                }
                case 3 -> {
                    System.out.println("================ EDITAR PLANO ==================");
                    Integer isPremium = editarPlanoPremium();
                    ouvinte.setPremium(isPremium);
                    ouvinteService.editarOuvinte(ouvinte);
                    usoValido = false;
                }
                case 4 -> {
                    System.out.println(4);
                    usoValido = false;
                }
                case 5 -> {
                    System.out.println(5);
                    System.out.println("================ EDITAR OUVINTE ==================");
                    String novoNome = getString("Novo nome: ");
                    ouvinte.setNome(novoNome);
                    String novaIdade = getString("Nova data de nascimento: (dd/MM/yyyy): ");
                    ouvinte.setDataNascimento(novaIdade);
                    String novoGenero = getString("Digite o genero : ");
                    ouvinte.setGenero(novoGenero);
                    Integer isPremium = editarPlanoPremium();
                    ouvinte.setPremium(isPremium);
                    ouvinteService.editarOuvinte(ouvinte);
                    usoValido = false;
                }
                case 6 -> {
                    usoValido = false;
                }
                default -> System.out.println(errorSelecionar);
            }
        }
    }

    public static Integer editarPlanoPremium(){
        int isPremium = 0;
        while(true){
            Menus.imprimirYellow("Deseja uma conta premium?");
            Integer tipo = Menus.getNumeric("[1] - Sim [0] - Não: ");
            if(tipo < 0 || tipo > 1){
                Menus.imprimirRed("OPS! Opção inválida, tente novamente.");
            } else {
                isPremium = tipo;
                break;
            }
        }
        return isPremium;
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
                            editarPlayListUsuario();
                        }
                    }
                    usoValido = false;
                }
                case 1 -> {
                    if(ouvinte.getPremium().equals(1)){
                        System.out.println("================= CRIAR PLAYLIST ==================");
                        String nomePlaylist = getString("Nome para playlist: ");

                        // CRIAR UMA NOVA PLAYLIST
                        Playlist playlist = new Playlist();
                        playlist.setNome(nomePlaylist);
                        playlist.setProprietario(ouvinte);
                        playListService.adicionarPlaylist(playlist);

                        // CHAMAR TELA PARA ADICIONAR MUSICA NA PLAYLIST SÓ SE PLAYLIST FOR CRIADA
                        if (playlist.getIdPlaylist() != null) {
                            addMusicasNaPlayList(playlist);
                        }
                        System.out.println("===================================================");
                    } else {
                        System.out.println("Usuário deve ser premium para criar playlist.");
                    }
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
                escolhaUser = getNumeric("Digite o id da musica: ");
                listaDeMusicaServices.adicionarMusicaNaPlayList(playlist, escolhaUser,
                        musicaService);
            } else {
                addMusica = false;
            }
        }
    }
    // Editar playlist usuário
    public static void editarPlayListUsuario() {
        escolhaUser = Menus.getNumeric("Digite o id da playlist: ");
        Playlist playlist = listaDeMusicaServices.getMusicasWithPlayList(escolhaUser, ouvinte);
        if(playlist != null) {
            if(!playlist.getMusicas().isEmpty()){
                for (Musica musica: playlist.getMusicas()) {
                    musica.imprimirMusica();
                }
                System.out.println("[0] - EDITAR NOME PLAYLIST      [1] - DELETAR MUSICA DA PLAYLIST");
                System.out.println("[2] - SAIR");
                escolhaUser = Menus.getNumeric();
                switch (escolhaUser) {
                    case 0 -> System.out.println("EDITAR PLAYLIST");
                    case 1 -> {
                        System.out.print("Digite o id da musica: ");
                        escolhaUser = Menus.getNumeric();
                        listaDeMusicaServices.deletarMusicaDaPlayList(playlist, ouvinte, escolhaUser);
                    }
                }
            } else {
                System.out.println("Playlist VAZIA!!!");
            }
        }else {
            System.out.println("OPS! Algum erro aconteceu!");
        }
    }

    // =============== OPÇÕES PARA ESCOLHA 6 - PERFIL DO USUÁRIO ====================
    public static void verInformacoesUser() {
        String ehPremium = ouvinte.getPremium() == 1 ? "Plano premium" : "Plano normal";
        System.out.println("=============== PERFIL DO USUÁRIO =================");
        System.out.println("Nome: " + ouvinte.getNome() + " |  Gênero: " + ouvinte.getGenero());
        System.out.println("Data de Nascimento: " + ouvinte.getDataNascimento() + " |  Plano: " + ehPremium);
        System.out.println("Id do usuário: " + ouvinte.getIdUser());
        System.out.println("===================================================");
        escolhaUser = Menus.getNumeric();
    }

    // MENU ALBUM
    public static void verAlbum(){
        AlbumService albumService = new AlbumService();
        //lsitando todos os albuns
        System.out.println("Aqui estao os seus albuns:");
        List<Album> listaAlbuns = new ArrayList<>();
        albumService.listarAlbum();

        //adicionando
        System.out.print("Adicione um album\nDigite o id do album: ");
        Album album = new Album();
        album.setNome(getString());
        albumService.adicionarAlbum(album);

    }

}
