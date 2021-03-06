package views;

import enums.Planos;
import exceptions.BancoDeDadosException;
import models.*;
import service.*;


import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Menus {
    public static MusicaService musicaService= new MusicaService();
    public static PlayListService playListService= new PlayListService();
    public static ListaDeMusicaServices listaDeMusicaServices = new ListaDeMusicaServices();
    public static SeguidoresService seguidoresService = new SeguidoresService();
    public static OuvinteService ouvinteService = new OuvinteService();

    //formatador apenas de exemplo adequar o formatador ao banco de dados
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static Ouvinte ouvinte;
    //*************************
    public static AlbumService albumService = new AlbumService();
    public static Artista artista = new Artista();
    public static ArtistaService artistaService = new ArtistaService();
    //*************************
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
        System.out.println("[0] - EDITAR NOME           [1] - EDITAR NASCIMENTO  ");
        System.out.println("[2] - EDITAR GENÊRO         [3] - EDITAR PLANO");
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
                    ouvinteService.editarOuvinte(ouvinte.getIdUser(), ouvinte);
                    usoValido = false;
                }
                case 1 -> {
                    System.out.println("================ EDITAR DATA DE NASCIMENTO ==================");
                    String novaIdade = getString("Nova data de nascimento: ");
                    ouvinte.setDataNascimento((LocalDate.parse(novaIdade, formatter)));
                    ouvinteService.editarOuvinte(ouvinte.getIdUser(), ouvinte);
                    usoValido = false;
                }
                case 2 -> {
                    System.out.println("================ EDITAR GENERO ==================");
                    String novoGenero = getString("Novo Gênero: ");
                    ouvinte.setGenero(novoGenero);
                    ouvinteService.editarOuvinte(ouvinte.getIdUser(), ouvinte);
                    usoValido = false;
                }
                case 3 -> {
                    System.out.println("================ EDITAR PLANO ==================");
                    Integer isPremium = editarPlanoPremium();
                    ouvinte.setPremium(isPremium);
                    ouvinteService.editarOuvinte(ouvinte.getIdUser(), ouvinte);
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
                    ouvinte.setDataNascimento(LocalDate.parse(novaIdade, formatter));
                    String novoGenero = getString("Digite o genero : ");
                    ouvinte.setGenero(novoGenero);
                    Integer isPremium = editarPlanoPremium();
                    ouvinte.setPremium(isPremium);
                    ouvinteService.editarOuvinte(ouvinte.getIdUser(), ouvinte);
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
            System.out.println("BEM AQUI");
            switch (tipo){
                case 0 ->{
                    isPremium = Planos.BASICO.getEscolha();
                }
                case 1 -> {
                    System.out.println("NÃPO SOU PREMIum");
                    isPremium = Planos.PREMIUM.getEscolha();
                }
                default -> {
                    Menus.imprimirRed("OPS! Opção inválida, tente novamente.");
                }
            }
            if(isPremium == 1 || isPremium == 0){
                break;
            }
        }
        return isPremium;
    }

    // MENU EXPLORAR MUSICA - ESCOLHA 1
    // Para o menu de informações do usuário
    public static void buscarMusica() {
        musicaService.listarMusica();
        System.out.println("================ EXPLORE MUSICAS ==================");
        System.out.println("[0] - BUSCAR MUSICA       [1] - VOLTAR");
        System.out.println("====================================================");
        escolhaUser = Menus.getNumeric();
        if(escolhaUser == 0) {
            String nomeMusica = getString("Busque o nome da musica: ");
            musicaService.filtrarMusica(nomeMusica);
            getString("Digite qualquer coisa para sair.");
        }
    }

    // MENU EXPLORAR MUSICA - ESCOLHA 2
//**********************************************************************************************************************
    public static void verArtista() {
        System.out.println("================ EXPLORAR ARTISTAS ==================");
        System.out.println("[0] - INFORMAÇÕES DO ARTISTA    [1] - BUSCAR ALBUNS");
        System.out.println("[2] - TOP 5 ARTISTAS            [3] - VOLTAR");
        System.out.println("=====================================================");
        escolhaUser = Menus.getNumeric();


        boolean usoValido = true;
        while(usoValido){
            switch(escolhaUser){
                case 0 ->{
                    System.out.println(0);
                    System.out.println("============== INFORMACOES DO ARTISTA ===============");
                    //listar todos os artistas para passar a informacao pelo id_artista
                    System.out.println("Esses sao os artistas cadastrados:");
                    artistaService.listarArtistas();
                    System.out.print("digite o ID do artista que voce quer informacoes: ");
                    escolhaUser = Menus.getNumeric();
//FALTA IMPLEMENTAR BUSCA DO ARTISTA PELO ID
                    System.out.println(artista.toString());

                    usoValido = false;
                }
                case 1 ->{
                    //ANTIGO BUSCAR ALBUM
                    System.out.println(0);
                    System.out.println("================= LISTAR ALBUNS =====================");
                    albumService.listarAlbum(); // listando todos os albuns...

                    //criar metodo buscarAlbum(id_artista)? -- Repository e Service

                    usoValido = false;
                }
                case 2 ->{
                    // procurar entender melhor a regra de negocio para implementar
                    System.out.println(2);
                    System.out.println("================= TOP 5 ARTISTAS =====================");

                    usoValido = false;
                }
                case 3 ->{
                    // sair
                    System.out.println(3);
                    usoValido = false;

                }
            }
        }
    }
//*************************************************************************************************************************
    // MENU SEGUIDORES - ESCOLHA 3
    public static void verSeguidores() {
        System.out.println("================== SEGUIDORES ======================");
        System.out.println("[0] - INFORMAÇÕES     [1] - VER PLAYLISTS/ALBUNS");
        System.out.println("[2] - VOLTAR");
        System.out.println("====================================================");
        escolhaUser = Menus.getNumeric();
        switch (escolhaUser) {
            case 0 -> {
                // QUEM SAO SEUS SEGUIDORES
                seguidoresService.getAllUsers(ouvinte);
                System.out.println("================= INFORMACOES =================");
                System.out.println("");
                seguidoresService.seguirUser(ouvinte, escolhaUser);

            }
            case 1 -> {
                seguidoresService.getAllSeguindo(ouvinte);
                System.out.println("============ VER PLAYLIST/ALBUNS ================");
                System.out.println("[0] - DEIXAR DE SEGUIR O USUÁRIO    [1] - VOLTAR");
                escolhaUser = Menus.getNumeric();
                if(escolhaUser == 0) {
                    escolhaUser = Menus.getNumeric("Deixar de seguir usuario (selecione id): ");
                    seguidoresService.deixarDeSeguirUsuario(ouvinte, escolhaUser);
                }
            }
            case 2 -> {
                seguidoresService.getAllSeguidores(ouvinte);
                Menus.getString("Digite algo para sair. ");
            }
        }
    }
//**********************************************************************************
    // MENU SEGUINDO - ESCOLHA 4
    public static void verSeguindo() {
        System.out.println("==================== USUÁRIOS ======================");
        System.out.println("[0] - VER LISTA DE USUÁRIOS    [1] - VER SEGUINDO");
        System.out.println("[2] - VER SEGUIDORES           [3] - VOLTAR");
        System.out.println("====================================================");
        escolhaUser = Menus.getNumeric();
        switch (escolhaUser) {
            case 0 -> {
                seguidoresService.getAllUsers(ouvinte);
                System.out.println("===================== OPÇÕES ======================");
                System.out.println("[0] - SEGUIR USUÁRIO    [1] - VOLTAR");
                escolhaUser = Menus.getNumeric();
                if(escolhaUser == 0) {
                    escolhaUser = Menus.getNumeric("Seguir usuario (selecione id): ");
                    ouvinteService.listarOuvintes();
                    seguidoresService.seguirUser(ouvinte, escolhaUser);
                }
            }
            case 1 -> {
                seguidoresService.getAllSeguindo(ouvinte);
                System.out.println("===================== OPÇÕES ======================");
                System.out.println("[0] - DEIXAR DE SEGUIR O USUÁRIO    [1] - VOLTAR");
                escolhaUser = Menus.getNumeric();
                if(escolhaUser == 0) {
                    escolhaUser = Menus.getNumeric("Deixar de seguir usuario (selecione id): ");
                    seguidoresService.deixarDeSeguirUsuario(ouvinte, escolhaUser);
                }
            }
            case 2 -> {
                seguidoresService.getAllSeguidores(ouvinte);
                Menus.getString("Digite algo para sair. ");
            }
        }

//        System.out.println("==================== SEGUINDO ======================");
//        System.out.println("[0] - INFORMAÇÕES    [1] - VER PLAYLISTS/ALBUNS");
//        System.out.println("[2] - VOLTAR");
//        System.out.println("====================================================");
//        escolhaUser = Menus.getNumeric();

    }

    // MENU BIBLIOTECA - ESCOLHA 5
    public static void verBibliotecas() {
        boolean usoValido = true;
        while (usoValido) {
            System.out.println("==================== BIBLIOTECA ===================");
            System.out.println("[0] - VER SUAS PLAYLISTS     [1] - CRIAR PLAYLIST");
            System.out.println("[2] - VOLTAR");
            System.out.println("===================================================");

            escolhaUser = Menus.getNumeric();
            switch (escolhaUser) {
                case 0 -> {
                    System.out.println("============== PLAYLISTS DO USUARIO ===============");
                    List<Playlist> playlists = playListService.ListarPlayList(ouvinte);
                    if(playlists.isEmpty()){
                        Menus.imprimirRed("Playlist vazia!! Adicione uma.");
                    }
                    System.out.println("===================================================");
                    if(!playlists.isEmpty()){
                        System.out.println("[0] - SELECIONAR PLAYLIST     [1] - SAIR");
                        escolhaUser = getNumeric();
                        if(escolhaUser == 0) {
                            editarPlayListUsuario();
                        }
                    }
                }
                case 1 -> {
                    if(ouvinte.getPremium().equals(Planos.PREMIUM.getEscolha())){
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
            System.out.println("================================================================");
            System.out.println("Playlist: " + playlist.getNome() + " | Quantidade de musicas: " + playlist.getMusicas().size());
            for (Musica musica: playlist.getMusicas()) {
                musica.imprimirMusica();
            }
            System.out.println();
            System.out.println("================= OPÇOES EDITAR PLAYLIST =======================");
            System.out.println("[0] - EDITAR NOME PLAYLIST      [1] - DELETAR MUSICA DA PLAYLIST");
            System.out.println("[2] - DELETAR PLAYLIST          [3] - ADICIONAR MUSICAS         ");
            System.out.println("[4] - SAIR                      ");
            System.out.println("================================================================");
            escolhaUser = Menus.getNumeric();
            switch (escolhaUser) {
                case 0 -> {
                    String novoNomePlayList = getString("Novo nome da playlist: ");
                    playListService.editarNomePlaylist(playlist, novoNomePlayList);
                }
                case 1 -> {
                    if(!playlist.getMusicas().isEmpty()){
                        escolhaUser = Menus.getNumeric("Digite o id da musica: ");
                        listaDeMusicaServices.deletarMusicaDaPlayList(playlist, ouvinte, escolhaUser);
                    } else {
                        Menus.imprimirRed("Playlist sem música!");
                    }
                }
                case 2 -> {
                    playListService.deletarPlaylist(playlist);
                }
                case 3 -> {
                    addMusicasNaPlayList(playlist);
                }

                default -> Menus.imprimirRed("Opção inválida!");
            }
        }else {
            Menus.imprimirRed("OPS! Algum erro aconteceu!!!");
        }
    }

    // =============== OPÇÕES PARA ESCOLHA 6 - PERFIL DO USUÁRIO ====================
    public static void verInformacoesUser() {
        String ehPremium = ouvinte.getPremium() == 1 ? String.valueOf(Planos.PREMIUM) : String.valueOf(Planos.BASICO);
        System.out.println("=============== PERFIL DO USUÁRIO =================");
        System.out.println("Nome: " + ouvinte.getNome() + "\nGênero: " + ouvinte.getGenero());
        System.out.println("Data de Nascimento: " + ouvinte.getDataNascimento() + "\nPlano: " + ehPremium);
        System.out.println("Id do usuário: " + ouvinte.getIdUser());
        System.out.println("===================================================");
        Menus.getString("Digite qualquer coisa para sair: ");
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
