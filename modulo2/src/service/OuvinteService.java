package service;

import exceptions.BancoDeDadosException;
import models.Ouvinte;
import repository.MusicaRepository;
import repository.OuvinteRepositorio;
import repository.UsuarioRepositorio;

import java.util.List;

import static views.Menus.ouvinte;


public class OuvinteService {

    private OuvinteRepositorio ouvinteRepositorio;

    private UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    public OuvinteService () {
        ouvinteRepositorio = new OuvinteRepositorio();
    }

    public void adicionarOuvinte (Ouvinte ouvinte){
        try{
            UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
            ouvinte = usuarioRepositorio.criarUsuario(ouvinte);
            if(ouvinte.getIdUser() != null){
                Ouvinte ouvinteAdicionado = ouvinteRepositorio.adicionar(ouvinte);
                System.out.println("Ouvinte adicionado com sucesso! "+ouvinteAdicionado);
            }


        }catch (BancoDeDadosException ex){
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO : "+ e.getMessage());
            e.printStackTrace();
        }
    }

    public void removerOuvinte (Integer id, Ouvinte ouvinte){
        try{
            boolean removeuComSucesso = ouvinteRepositorio.remover(id);
            System.out.println("Ouvinte removido?  "+ removeuComSucesso + " com id = "+id);

        } catch (BancoDeDadosException ex){
            ex.printStackTrace();
        }
    }

    public void editarOuvinte (Integer id, Ouvinte ouvinte){
        try {
            boolean editouComSucesso = usuarioRepositorio.editar(id,ouvinte);
            System.out.println("Ouvinte editado? "+ editouComSucesso + " com id = "+id);
        }catch (BancoDeDadosException ex ){
            ex.printStackTrace();
        }
    }

    public void listarOuvintes () {
        try {
            List<Ouvinte> listarOuvintes = usuarioRepositorio.listar();
            listarOuvintes.forEach(System.out::println);
        }catch (BancoDeDadosException ex){
            ex.printStackTrace();
        }
    }

    public void consultarOuvinte (Integer id) {
        ouvinteRepositorio.getOuvinte(ouvinte.getIdOuvinte());
        System.out.println(ouvinte.toString());

    }

}
