package service;

import abstracts.Usuario;
import exceptions.BancoDeDadosException;
import models.Ouvinte;
import repository.SeguidoresRepository;
import repository.UsuarioRepositorio;
import views.Menus;

import java.util.List;

public class SeguidoresService {

    private static SeguidoresRepository  seguidoresRepository;
    private static UsuarioRepositorio usuarioRepositorio;

    public SeguidoresService() {
        seguidoresRepository = new SeguidoresRepository();
        usuarioRepositorio = new UsuarioRepositorio();
    }

    public void getAllSeguidores(Ouvinte ouvinte){
        try {
            List<Usuario> usuarios = seguidoresRepository.getAllSeguidores(ouvinte.getIdOuvinte());
            usuarios.forEach(Usuario::imprimirInformacoesPersonalizadas);
        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("Error ao buscar seguidores!");
        }
    }

    public void getAllSeguindo(Ouvinte ouvinte){
        try {
            List<Usuario> usuarios = seguidoresRepository.getAllSeguindo(ouvinte.getIdOuvinte());
            usuarios.forEach(Usuario::imprimirInformacoesPersonalizadas);
        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("Error ao buscar os seguindo!");
        }
    }

    public void getAllUsers(Ouvinte ouvinte){
        try {
            List<Usuario> usuarios = usuarioRepositorio.getAllUser(ouvinte.getIdOuvinte());
            System.out.println("------------------------------------------------");
            usuarios.forEach(Usuario::imprimirInformacoesPersonalizadas);
            System.out.println("------------------------------------------------");

        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("Error ao buscar lista de usuarios!");
        }

    }

    public void seguirUser(Ouvinte ouvinte, Integer idSeguindo) {
        try {
            seguidoresRepository.seguirUsuario(idSeguindo, ouvinte);
        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("Você já segue o usuário");
        }
    }

    public void deixarDeSeguirUsuario(Ouvinte ouvinte, Integer idSeguindo) {
        try {
            seguidoresRepository.deixarDeSeguirUsuario(idSeguindo, ouvinte);
        } catch (BancoDeDadosException e) {
            Menus.imprimirRed("Erro ao deixar de seguir o usuário.");
        }
    }

}
