package repository;

import abstracts.Usuario;
import exceptions.BancoDeDadosException;
import models.Ouvinte;

import java.sql.*;

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
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);


            stmt.setInt(1, ouvinte.getIdUser());
            stmt.setString(2, ouvinte.getNome());
            stmt.setString(3, ouvinte.getDataNascimento());
            stmt.setString(4, ouvinte.getGenero());
            stmt.setString(5, ouvinte.getPremium());
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
}
