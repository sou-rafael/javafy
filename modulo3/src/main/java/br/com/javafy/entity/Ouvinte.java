package br.com.javafy.entity;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Ouvinte extends Usuario {
    @NotNull
    @Size(min = 1, max = 9)
    private Integer idOuvinte;

    private List<PlayList> playlists = new ArrayList<>();

}
