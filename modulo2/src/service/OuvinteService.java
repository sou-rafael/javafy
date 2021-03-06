package service;

import enums.Planos;
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
            ouvinte = usuarioRepositorio.adicionar(ouvinte);
            if(ouvinte.getIdUser() != null){
                ouvinte = ouvinteRepositorio.adicionar(ouvinte);
            }


        }catch (BancoDeDadosException ex){
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removerOuvinte (Integer id, Ouvinte ouvinte){
        try{
            boolean removeuComSucesso = ouvinteRepositorio.remover(id);

        } catch (BancoDeDadosException ex){
            ex.printStackTrace();
        }
    }

    public void editarOuvinte (Integer id, Ouvinte ouvinte){
        try {
            boolean editouComSucesso = usuarioRepositorio.editar(id,ouvinte);
        }catch (BancoDeDadosException ex ){
            ex.printStackTrace();
        }
    }

    public void listarOuvintes () {
        try{
            List <Ouvinte> ouvintes = usuarioRepositorio.listar();
            ouvintes.forEach(System.out::println);

        }catch (BancoDeDadosException ex){
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
                System.out.println("BEM AQUI");
                switch (tipo){
                    case 0 ->{
                        isPremium = Planos.BASICO.getEscolha();
                    }
                    case 1 -> {
                        System.out.println("NÃPO SOU PREMIum");
                        isPremium = Planos.PREMIUM.getEscolha();
                    }
                    default -> {
                        Menus.imprimirRed("OPS! Opção inválida, tente novamente.");
                    }
                }
                if(isPremium == 1 || isPremium == 0){
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
