package repository;

import abstracts.Usuario;
import exceptions.BancoDeDadosException;
import models.Ouvinte;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsuarioRepositorio {

    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try{
            String sql = "SELECT seq_id_user.nextval usuarioSequence FROM USUARIO";
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            if(result.next()){
                return result.getInt("usuarioSequence");
            }
            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    public Ouvinte criarUsuario (Ouvinte ouvinte) throws BancoDeDadosException {
        Connection con = null;
        try{

            con = ConexaoBancoDeDados.getConnection();
            Integer idUsuario = this.getProximoId(con);
            System.out.println(idUsuario);
            ouvinte.setIdUser(idUsuario);

            String sql = "INSERT INTO USUARIO\n" +
                    "(ID_USER, NOME, DATA_NASCIMENTO, GENERO, PREMIUM)\n" +
                    "VALUES(?, ?, TO_DATE( ?, 'DD/MM/YYYY' ), ?, ? )\n";

            PreparedStatement stmt = con.prepareStatement(sql);


            stmt.setInt(1, ouvinte.getIdUser());
            stmt.setString(2, ouvinte.getNome());
            stmt.setString(3, ouvinte.getDataNascimento());
            stmt.setString(4, ouvinte.getGenero());
            stmt.setInt(5, ouvinte.getPremium());
            int res = stmt.executeUpdate();
            System.out.println("adicionarUsuario.res=" + res);
            return ouvinte;

        }catch (SQLException ex){
            //colocar nossa exception
            throw new BancoDeDadosException(ex.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean editar(Ouvinte ouvinte) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET \n");
            sql.append(" genero = ?,");
            sql.append(" premium = ?,");
            sql.append(" nome = ? ,");
            sql.append(" data_nascimento = ? ");
            sql.deleteCharAt(sql.length() - 1); //remove o ultimo ','
            sql.append(" WHERE id_user = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            assert ouvinte != null;

            stmt.setString(1, ouvinte.getGenero());
            stmt.setInt(2, ouvinte.getPremium());
            stmt.setString(3, ouvinte.getNome());

            System.out.println(ouvinte.getDataNascimento());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(ouvinte.getDataNascimento(), formatter);
            stmt.setDate(4, Date.valueOf(date));
            stmt.setInt(5, ouvinte.getIdUser());
            int res = stmt.executeUpdate();
            return res > 0;
        } catch (SQLException ex) {
            throw new BancoDeDadosException(ex.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
