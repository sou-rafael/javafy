package utils;

import abstracts.PlayListAbstract;
import abstracts.User;
import models.Musica;
import models.Ouvinte;
import models.Playlist;
import utils.bd.AlbunsListaBD;
import utils.bd.PlayListsBD;
import utils.models.PlayListUtils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Menus {
    public static Scanner scanner = new Scanner(System.in);
    //public static UUID uuid = UUID.randomUUID();
    public static PlayListsBD playListsBD = new PlayListsBD();
    public static AlbunsListaBD albunsListaBD = new AlbunsListaBD();

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
        System.out.println("[5] - Tudo            [6] - VOLTAR     ");
        System.out.println("-------------------------------------------");
        System.out.print("Escolha: ");
    }

    public static void suasInformacoes(Ouvinte ouvinte){
        System.out.println(ouvinte);
        Boolean voltarMenuPrincipal = false;
        System.out.print("Digite qualquer coisa para voltar: : ");
        String escolha = Menus.scanner.nextLine();
    }

    public static void verSeguidores(Ouvinte ouvinte){
        for (User ovt:
             ouvinte.getSeguidores()) {
            ovt.informacoesMinimas();
        }
        System.out.print("Digite qualquer coisa para voltar: ");
        String escolha = Menus.scanner.nextLine();
    }


    public static void atualizarInformacoes(Ouvinte ouvinte){
        boolean voltarParaMenuPrincipal = false;
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
                    System.out.println("Alterando todos os campos:");
                    System.out.print("Novo nome: ");
                    ouvinte.setNome(Menus.scanner.nextLine());
                    //------------
                    System.out.print("Data de nascimento: ");
                    ouvinte.setDataDeNascimento(Menus.scanner.nextLine());
                    //-------------
                    System.out.print("Gênero: ");
                    ouvinte.setGenero(Menus.scanner.nextLine());
                    //-------------
                    System.out.print("Novo plano:\nPlano: 1 - Premium  2 - Normal: ");
                    int novoPlano = Menus.scanner.nextInt();
                    Menus.scanner.nextLine();
                    if(novoPlano < 1 || novoPlano > 2){
                        System.out.println("OPS! Opção inválida, tente novamente.");
                    } else {
                        ouvinte.setPremium(novoPlano == 1);
                        System.out.println("Editado com sucesso!!! \n");
                        break;
                    }

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

    public static void  menuBiblioteca(){
        System.out.println("-----------------------------------------------");
        System.out.println("[1] - Ver minhas playlist  [2] - Criar PlayList");
        System.out.println("[QUALQUER BOTÃO] - Voltar   ");
        System.out.println("-----------------------------------------------");
        System.out.print("Escolha: ");
    }

    public static void bibliotecaEscolha(Ouvinte ouvinte){
        boolean voltarMenuInicial = true;
        do{
            Menus.menuBiblioteca();
            String escolha = Menus.scanner.nextLine();
            switch (escolha){
                case "1":
                    Menus.menuLerPlayList(ouvinte);
                    break;
                case "2":
                    if (ouvinte.isPremium()){
                        Menus.menuCriarPlayList(ouvinte);
                    } else {
                        System.out.println("Para criar playlist, você deve ser premium!!");
                        System.out.println("Atualize seu plano em editar perfil.");
                    }
                    break;
                default:
                    voltarMenuInicial = false;
            }
        } while (voltarMenuInicial);
    }

    public static void menuLerPlayList(Ouvinte ouvinte){
        System.out.println("------------------Suas playlist-----------------");
        for (int i = 0; i < ouvinte.getPlaylists().size(); i ++){
            String id = ouvinte.getPlaylists().get(i);
            PlayListUtils playListUtils = playListsBD.read(id);
            if(playListUtils == null) {
                System.out.println("Ops! Playlist não encontrada");
            } else {
                System.out.println(i + " - " + playListUtils.getPlaylist().getNomePlaylist()
                        + ", quantidade de musicas " + playListUtils.getPlaylist().getListaMusicas().size());
            }
        }
    }

    public static ArrayList<Musica> getListMusica(){
        List<Playlist> playlists = albunsListaBD.getAlbunsListMap().values().stream()
                .map(PlayListAbstract::getPlaylist).collect(Collectors.toList());
        List<ArrayList> musicasList = playlists.stream()
                .map(playlist1 -> playlist1.getListaMusicas()).collect(Collectors.toList());
        ArrayList<Musica> musicas = new ArrayList<>();
        for(ArrayList<Musica> mscs: musicasList){
            for (Musica msc: mscs){
                musicas.add(msc);
            }
        }
        return musicas;
    }
    public static void buscarMusica(){
        ArrayList<Musica> musicas = getListMusica();
        for(Musica musica: musicas){
            System.out.println("Musica: " + musica.getNomeDaMusica()+ ", curtidas: " + musica.getCurtidas());
        }
        System.out.print("Entre com nome da musica: ");
        String busca = Menus.scanner.nextLine();
        ArrayList<Musica> listaFiltrada = (ArrayList<Musica>) musicas.stream()
                .filter(musica -> musica.getNomeDaMusica().toLowerCase().contains(busca.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\n\n");
        for(Musica filtrada: listaFiltrada){
            System.out.println("Musica: " + filtrada.getNomeDaMusica()+ ", curtidas: " + filtrada.getCurtidas());
        }
    }

    public static void menuCriarPlayList(Ouvinte ouvinte){
        UUID uuid = UUID.randomUUID();

        System.out.println("-----------------------------------------------");
        System.out.print("Nome da playlist: ");
        String nomePlayList = Menus.scanner.nextLine();

        Playlist playlist = new Playlist(nomePlayList, uuid.toString(), ouvinte);

        boolean resposta = playListsBD.criar(playlist);


        if(resposta){
            ouvinte.getPlaylists().add(playlist.getId());
            System.out.println("PlayList Criada");
        } else{
            System.out.println("Error ao criar a playlist");
        }

        System.out.println("----------------------------------------------------");
        System.out.println("[1] - Adicionar Musicas    [QUALQUER BOTÃO] - Voltar");
        System.out.println("----------------------------------------------------");
        System.out.print("Escolha: ");
        String escolhas  = Menus.scanner.nextLine();

        if(escolhas.equals("1")){
            ArrayList<Musica> musicas = getListMusica();
            while (true){
                System.out.println("------------------------Lista Musicas---------------");
                for (int i = 0; i < musicas.size(); i++) {
                    System.out.println("id: " + i + "  " + musicas.get(i));
                }
                System.out.println("----------------------------------------------------");
                System.out.println("Adicionar musica id: ");
                Integer posicao = Menus.scanner.nextInt();
                Menus.scanner.nextLine();

                if(posicao < 0 || posicao >= musicas.size()){
                    System.out.println("Ops! Tente novamente");
                } else {
                    Musica musica = musicas.get(posicao);
                    playListsBD.adicionarMusicas(ouvinte, playlist.getId(), musica);
                    System.out.print("Adicionar mais musicas? [1] - Sim [Qualquer] - Não");
                    String escolha = Menus.scanner.nextLine();
                    if(!escolha.equals("1")){
                        break;
                    }
                }
            }
        }

    }
}
