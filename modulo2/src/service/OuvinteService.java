package service;

import exceptions.BancoDeDadosException;
import models.Ouvinte;
import repository.OuvinteRepositorio;
import repository.UsuarioRepositorio;
import views.Menus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;



public class OuvinteService {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //18/10/2020

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

    public void editarOuvinte ( Ouvinte ouvinte){
        try {
            boolean editouComSucesso = usuarioRepositorio.editar(ouvinte);
        }catch (BancoDeDadosException ex ){
            ex.printStackTrace();
        }
    }

    public void criarOuvinte () {

        System.out.println("==================== CRIAR CONTA ====================");
        while (true) {
            String nome = Menus.getString("Nome do usuário: ");
            String dataNascimento = Menus.getString("Data de nascimento: ");
            String genero = Menus.getString("Seu genêro: ");
            int isPremium = 0;

            while(true){
                Menus.imprimirYellow("Deseja uma conta premium?");
                Integer tipo = Menus.getNumeric("[1] - Sim [0] - Não: ");
                if(tipo < 0 || tipo > 1){
                    Menus.imprimirRed("OPS! Opção inválida, tente novamente.");
                } else {
                    isPremium = tipo;
                    break;
                }
            }

            Menus.ouvinte = new Ouvinte(null,nome, LocalDate.parse(dataNascimento, formatter),genero, isPremium,null);

            adicionarOuvinte(Menus.ouvinte);

            if(Menus.ouvinte.getIdOuvinte() != null){
                Menus.imprimirBlue("Conta criada com sucesso.");
                break;
            }  else {
                Menus.imprimirRed("Erro ao criar a conta");
            }
        }
    }

}
