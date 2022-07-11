package br.com.javafy.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Artista extends Usuario{
    private Integer idArtista;
    private String bio;
    private Integer avaliacao;
}
