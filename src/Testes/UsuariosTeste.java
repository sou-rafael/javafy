package Testes;

import models.Album;
import models.Artista;
import models.Musica;
import models.Ouvinte;
import utils.Menus;

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

    public static void inicializarMusicasArtistas(){
        String bio = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s";

        Artista artistaPericles = new Artista("Pericles", UUID.randomUUID().toString(),
                "22-06-1969", "Ma", true, bio, 4.0);
        Album albumPericles = new Album("Deserto de ilusões",
                UUID.randomUUID().toString(), artistaPericles, 4.5, "Pagode");
        Musica mscPe1 = new Musica(UUID.randomUUID().toString(), "Areia Movediça",artistaPericles,
                4.5, 33);
        Musica mscPe2 = new Musica(UUID.randomUUID().toString(), "Constumes Iguais", artistaPericles,
                4.8, 525);
        Musica mscPe3 = new Musica(UUID.randomUUID().toString(), "Trinta graus", artistaPericles,
                3.7, 1214);
        ArrayList<Musica> musicasPericles = new ArrayList<>(Arrays.asList(mscPe1, mscPe2, mscPe3));

        albumPericles.getListaMusicas().addAll(musicasPericles);


        Artista artistaMarisa = new Artista("Marisa Monte", UUID.randomUUID().toString(),
                "1-07-1967", "Fe", true, bio, 4.5);
        Album albumMarisa = new Album("MM",
                UUID.randomUUID().toString(), artistaMarisa, 4.5, "MPB");
        Musica mscM1 = new Musica(UUID.randomUUID().toString(), "Bem que se quis",artistaMarisa, 2.5, 555);
        Musica mscM2 = new Musica(UUID.randomUUID().toString(), "Depois", artistaMarisa,
                2.3, 1122);
        Musica mscM3 = new Musica(UUID.randomUUID().toString(), "Vilareijo", artistaMarisa,
                3.3, 144);
        Musica mscM4 = new Musica(UUID.randomUUID().toString(), "Trinta Vilareijo", artistaMarisa,
                3.6, 154);

        ArrayList<Musica> musicasMarisa = new ArrayList<>(Arrays.asList(mscM1, mscM2, mscM3, mscM4));
        albumMarisa.getListaMusicas().addAll(musicasMarisa);

        Menus.albunsListaBD.criar(albumPericles);
        Menus.albunsListaBD.criar(albumMarisa);

    }



    public static void main(String[] args) throws IOException {
        // Iniciando objetos importantes
        //Ouvinte ouvinte = createOuvinte();
        inicializarMusicasArtistas();
        UUID uuid = UUID.randomUUID();
        Ouvinte ouvinte = new Ouvinte("Cleber", uuid.toString(),
                "13101994", "M", false );

        Ouvinte ouvinte2 = new Ouvinte("Juliana", uuid.toString(),
                "22051998", "F", true );

        Ouvinte ouvinte3 = new Ouvinte("Rafael", uuid.toString(),
                "77766687", "M", false );

        Ouvinte ouvinte4 = new Ouvinte("Rodrigo", uuid.toString(),
                "444555666", "M", true );

        ouvinte2.seguirUser(ouvinte);
        ouvinte3.seguirUser(ouvinte);

        //adicionando pessoas na lista de seguindo
        ouvinte4.userSeguindo(ouvinte);


        boolean finalizarAplicacao = false;
        do{
            Menus.menuEscolha();
            String escolha = Menus.scanner.nextLine();

            switch (escolha) {
                case "1":
                    Menus.atualizarInformacoes(ouvinte);
                    break;
                case "2":
                    Menus.buscarMusica();
                    break;
                case "3":
                    Menus.listarArtistas(ouvinte);
                    break;
                case "4":
                    Menus.verSeguidores(ouvinte);
                    break;
                case "5":
                    Menus.verSeguindo(ouvinte);
                    break;
                case "6":
                    Menus.bibliotecaEscolha(ouvinte);
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