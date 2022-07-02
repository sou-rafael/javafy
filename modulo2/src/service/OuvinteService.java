package service;

import exceptions.BancoDeDadosException;
import models.Ouvinte;
import repository.OuvinteRepositorio;
import repository.UsuarioRepositorio;

import java.util.List;

public class OuvinteService {

    private OuvinteRepositorio ouvinteRepositorio;

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
            boolean editouComSucesso = ouvinteRepositorio.editar(id,ouvinte);
            System.out.println("Ouvinte editado? "+ editouComSucesso + " com id = "+id);
        }catch (BancoDeDadosException ex ){
            ex.printStackTrace();
        }
    }

    public void listarOuvintes () {
        try {
            List<Ouvinte> listarOuvintes = ouvinteRepositorio.listar();
            listarOuvintes.forEach(System.out::println);
        }catch (BancoDeDadosException ex){
            ex.printStackTrace();
        }
    }

    public void consultarOuvinte (Integer id) {

        Ouvinte ouvinte = ouvinteRepositorio.getOuvinte(id);
        ouvinte.toString();

    }

}
