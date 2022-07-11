package br.com.javafy.entity;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Usuario {

    private Integer idUser;
    @NotBlank(message = "Nome não pode ser nulo")
    private String nome;
    @NotNull
    @Past
    private LocalDate dataNascimento;

    private String genero;
    @NotNull
    private Integer premium;

    @Email
    @NotBlank(message = "Email é obrigatorio")
    private String email;

    //private List<Usuario> seguidores = new ArrayList<>();
    //private List<Usuario> seguindo = new ArrayList<>();

}
