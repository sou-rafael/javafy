package br.com.javafy.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Musica {
    private Integer idMusica;
    private Album album;
    private String nome;
    private Integer avaliacao;
    private Double duracao;
    private Integer curtidas;
}
