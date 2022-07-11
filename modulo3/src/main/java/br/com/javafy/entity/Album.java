package br.com.javafy.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Album extends PlayListModel<Artista>{
    private Integer idAlbum;
    private String biografia;
    private Integer avaliacao;
}
