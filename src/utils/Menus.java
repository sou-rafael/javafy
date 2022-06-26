package utils;

import abstracts.PlayListAbstract;
import abstracts.User;
import models.Musica;
import models.Ouvinte;
import models.Playlist;
import utils.bd.AlbunsListaBD;
import utils.bd.PlayListsBD;
import utils.models.PlayListUtils;

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
        System.out.println("[3] - Listar Artistas   [4] - Ver seguidores");
        System.out.println("[5] - Ver seguindo      [6] - Biblioteca    ");
        System.out.println("[7] - Suas informações  [8] - Sair          ");
        System.out.println("-------------------------------------------");
        System.out.print("Escolha: ");
    }

    public static void listarArtistas (Ouvinte ouvinte) {
        System.out.println("-------------------------------------------");
        System.out.println("Listando artistas : ");
        ArrayList <User> listaArtistas= albunsListaBD.listar();
        for (int i = 0; i < listaArtistas.size(); i++){
            System.out.println("id - "+i +" artista: "+ listaArtistas.get(i).getNome());
        }

        System.out.println("[1] - Seguir artista     Digite qualquer coisa para voltar: : ");
        String escolha = Menus.scanner.nextLine();
        switch (escolha){
            case "1" -> {
                System.out.print("Digite o id do artista: ");
              Integer numero =  Menus.scanner.nextInt();
              Menus.scanner.nextLine();
              if(numero >= 0 && numero < listaArtistas.size()){
                  User artista = listaArtistas.get(numero);
                 if(ouvinte.getSeguindo().contains(artista)) {
                     System.out.println("O usuario já segue este artista");
                     System.out.println("[1] - Para deixar de seguir  [Qualquer coisa] - Sair ");
                     escolha = scanner.nextLine();
                     switch (escolha){
                         case "1" -> {
                             ouvinte.getSeguindo().remove(artista);
                             System.out.println("Você deixou de seguir "+artista.getNome());
                         }
                         default -> {

                         }
                     }

                 }else{
                     ouvinte.getSeguindo().add(artista);
                 }

              }else{
                  System.out.println("Artista não encontrado !");
              }

            }
        }
        System.out.println("-------------------------------------------");
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
        for (User ovt:ouvinte.getSeguidores()) {
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
        System.out.println("[X] - Voltar   ");
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

    public static void editarPlayList(Ouvinte ouvinte, ArrayList<Playlist> playlists){
        System.out.println("[1] - Editar PlayList [2] - Excluir PlayList \n[X] - Sair");
        System.out.print("Selecionar: ");
        String escolha = Menus.scanner.nextLine();

        Integer escolhaId = 0;
        if(escolha.equals("1") || escolha.equals("2")){
            System.out.print("Id da playlist: ");
            escolhaId = Menus.scanner.nextInt();
            Menus.scanner.nextLine();
        }

        switch (escolha){
            case "1":
                if(escolhaId < 0 || escolhaId >= playlists.size() ){
                    System.out.println("Id inválido");
                } else {
                    Playlist playlist = playlists.get(escolhaId);
                    System.out.println("------------------Edição Playlist----------------------");
                    System.out.println("Nome Playlist: " + playlist.getNomePlaylist());
                    int cont = 0;
                    for (Playlist playl: playlists){
                        for (Musica m: playl.getListaMusicas()){
                            System.out.println("id - " + cont+ " | Musica: " + m.getNomeDaMusica());
                            cont ++;
                        }
                    }
                    System.out.println("-----------------------------------------------");
                    System.out.println("[1] - Editar nome Playlist" +
                            " [2] - Remover Musica  [3] - Sair");
                    System.out.print("Escolha: ");
                    Integer escolhaEditPlaylist = Menus.scanner.nextInt();
                    Menus.scanner.nextLine();

                    if(escolhaEditPlaylist == 1){
                        System.out.print("Novo nome da playlist: ");
                        String novoNome = Menus.scanner.nextLine();
                        playListsBD.atualizar(ouvinte, playlist.getId(), novoNome);
                    } else if(escolhaEditPlaylist == 2) {
                        System.out.print("Id da musica: ");
                        Integer idMusica = Menus.scanner.nextInt();
                        Menus.scanner.nextLine();

                        if(idMusica < 0 || idMusica >= playlist.getListaMusicas().size()){
                            return;
                        } else {
                            Musica musica = playlist.getListaMusicas().get(idMusica);
                            boolean removido = playListsBD
                                    .remover(ouvinte, musica.getId(), playlist.getId());
                            if(removido){
                                // Por alguma razão temos que atualizar
                                playlist.getListaMusicas().removeIf(msc -> msc.getId().equals(idMusica));
                            }
                        }
                    }
                }
                break;
            case "2":
                if(escolhaId < 0 || escolhaId >= playlists.size() ){
                    System.out.println("Id inválido");
                } else {
                    Playlist playlist = playlists.get(escolhaId);
                    playListsBD.deletar(playlist);
                }
                break;
        }
    }

    public static void menuLerPlayList(Ouvinte ouvinte){
        ArrayList<Playlist> playlists = new ArrayList<>();
        System.out.println("------------------Suas playlist-----------------");
        for (int i = 0; i < ouvinte.getPlaylists().size(); i ++){
            String id = ouvinte.getPlaylists().get(i);
            PlayListUtils playListUtils = playListsBD.read(id);
            if(playListUtils == null) {
                System.out.println("Ops! Playlist não encontrada");
            } else {
                playlists.add(playListUtils.getPlaylist());
                System.out.println("id: " + i + " - Nome playlist: " + playListUtils.getPlaylist().getNomePlaylist()
                        + ". Quantidade de musicas " + playListUtils.getPlaylist().getListaMusicas().size());
            }
        }
        if(!playlists.isEmpty()){
            System.out.println("-----------------------------------------------");
            editarPlayList(ouvinte, playlists);
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
                    Musica msc = musicas.get(i);
                    System.out.println("id: " + i + " | Nome: "
                            + msc.getNomeDaMusica() + " | Artista: " + msc.getArtista().getNome());
                }
                System.out.println("----------------------------------------------------");
                System.out.print("Adicionar musica id: ");
                Integer posicao = Menus.scanner.nextInt();
                Menus.scanner.nextLine();

                if(posicao < 0 || posicao >= musicas.size()){
                    System.out.println("Ops! Tente novamente");
                } else {
                    Musica musica = musicas.get(posicao);
                    boolean addMusica = playListsBD
                            .adicionarMusicas(ouvinte, playlist.getId(), musica);
                    if(addMusica){
                        System.out.println("Musica adicionada");
                    } else {
                        System.out.println("Ops! Já está na lista");
                    }
                    System.out.print("Adicionar mais musicas? [1] - Sim [X] - Não: ");
                    String escolha = Menus.scanner.nextLine();
                    if(!escolha.equals("1")){
                        break;
                    }
                }
            }
        }

    }
}
