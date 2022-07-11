package br.com.javafy.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlayListModel<T> {
    private Integer idPlaylist;
    private String nome;
    private T proprietario;
    private List<Musica> musicas = new ArrayList<>();
}
