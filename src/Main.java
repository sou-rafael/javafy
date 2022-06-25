import models.Musica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Musica msc1 = new Musica("001", 3, 4.4, 20);
        Musica msc2 = new Musica("001", 3, 4.4, 20);
        Musica msc3 = new Musica("001", 3, 4.4, 20);
        Musica msc4 = new Musica("001", 3, 4.4, 20);

        List<Musica> musicas = new ArrayList<>(Arrays.asList(msc1, msc2, msc3, msc4));

        Integer somaAvaliacoes = musicas.stream().mapToInt(Musica::getAvaliacao).sum();
        System.out.println("Soma das avaliações das musicas " + somaAvaliacoes);


    }
}
